package event.recommendation.system.services.event;

import event.recommendation.system.dto.EventDTO;
import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.EventSpace;
import event.recommendation.system.entities.Tag;
import event.recommendation.system.entities.User;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.managers.UserManager;
import event.recommendation.system.repositories.EventRepository;
import event.recommendation.system.services.event.strategy.*;
import event.recommendation.system.services.user.UserService;
import event.recommendation.system.subscription.subscriber.observable.NotificationSubscription;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.*;

import static event.recommendation.system.enums.EventType.NONE;
import static event.recommendation.system.enums.SubscriptionType.CREATION;
import static event.recommendation.system.menu.MenuTabs.getDefaultMenu;
import static event.recommendation.system.menu.MenuTabs.getDefaultSlideMenu;
import static java.time.LocalDate.now;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;


/*TODO: refactor*/
@Service
@RequiredArgsConstructor
public class EventService {
    private final UserManager userManager = new UserManager();
    private final EventRepository eventRepository;
    private final EventValidator eventValidator;
    private final EventAdapter eventAdapter;
    private final UserService userService;
    private final ApplicationContext context;

    private final NotificationSubscription notificationSubscription;

    public void addNewEvent(EventDTO eventDTO, EventSpace eventSpace, User user) {
        Event newEvent = eventAdapter.adapt(eventDTO, eventSpace, user);
        //if (eventValidator.isValid(newEvent, model)) {
            saveWithEventSpace(newEvent);
            notificationSubscription.notifySubscribers(newEvent, CREATION);
        //}
    }

    public Event saveWithEventSpace(Event event) {
        return eventRepository.save(event);
    }

    public void addEventsModelAttributes(EventType eventType, Model model) {
        Map<String, List<Event>> events = getWeekEvents(eventType);
        List<Event> availableEvents = filterEventsAvailableToRegistration(events, userManager.getLoggedInUser());
        model.addAttribute("menuElements", getDefaultMenu());
        model.addAttribute("slideMenuElements", getDefaultSlideMenu());
        model.addAttribute("eventTypes", EventType.getEventsTypes());
        model.addAttribute("events", events);
        model.addAttribute("availableEvents", availableEvents);
        model.addAttribute("user", userManager.getLoggedInUser());
        model.addAttribute("isUserAdmin", userManager.isLoggedUserAdmin());
    }

    public List<Event> filterEventsAvailableToRegistration(Map<String, List<Event>> events, User user) {
        return filterEventsAvailableToRegistration(getAllEvents(events), user);
    }

    private List<Event> getAllEvents(Map<String, List<Event>> events) {
        return events.values().stream()
                .flatMap(List::stream)
                .collect(toList());
    }

    private List<Event> filterEventsAvailableToRegistration(List<Event> events, User user){
        List<Event> availableEvents = new ArrayList<>(events);
        availableEvents.removeIf( e -> user.getEvents().stream().anyMatch(userEvent -> userEvent.getId().equals(e.getId())));
        return availableEvents;
    }

    public Map<String, List<Event>> getUserWeekEvents(String eventType) {
        EventType type = StringUtils.isBlank(eventType) ? NONE : EventType.valueOf(eventType);
        return getWeekEvents(getEventsService(type));
    }

    public Map<String, List<Event>> getWeekEvents(EventType eventType) {
        return getWeekEvents(getEventsService(eventType));
    }

    private Map<String, List<Event>> getWeekEvents(EventsService strategyEventService) {
        return strategyEventService.filterEvents();
    }

    private EventsService getEventsService(EventType eventType) {
        if (isNull(eventType)) {
            return context.getBean(DefaultUserEventService.class);
        }

        return switch (eventType) {
            case SPORT -> context.getBean(SportEventService.class);
            case ART -> context.getBean(ArtEventService.class);
            case EDUCATION -> context.getBean(EducationEventService.class);
            default -> context.getBean(DefaultUserEventService.class);
        };
    }

    public void deleteEvent(Event event) {
        event.setActive(false);
        eventRepository.save(event);
    }

    public void renew(Event event) {
        event.setActive(true);
        eventRepository.save(event);
    }

    public void cancelRegistration(User user, Event event) {
        user.getEvents().removeIf(userEvent -> userEvent.getId().equals(event.getId()));
        event.getParticipants().removeIf(usr -> usr.getId().equals(user.getId()));
        userService.save(user);
        eventRepository.save(event);
    }

    public void registerUserForEvent(User user, Event event) {
        user.getEvents().add(event);
        userService.save(user);
    }

    public List<Event> getAllFromToday() {
        return eventRepository.getByDateAfter(now())
                .orElseGet(Collections::emptyList);
    }

    public Optional<List<Event>> getActiveAndActualEventsByTypesSortedByRate(Set<EventType> eventTypes) {
        return eventRepository.getByTypeInAndDateAfter(eventTypes, now());
    }

    public Optional<List<Event>> getByTags(Set<Tag> tags) {
        return eventRepository.getByTagsInAndDateAfter(tags, now());
    }

    public List<Event> getByCreatorAndDateEqualsOrDateAfter(User creator, LocalDate date) {
        return eventRepository.getByCreatorIdAndDateEqualsOrDateAfter(creator.getId(), date, date)
                .orElseGet(Collections::emptyList);
    }
}
