package event.recommendation.system.services.event;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.EventSpace;
import event.recommendation.system.entities.Tag;
import event.recommendation.system.entities.User;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.managers.UserManager;
import event.recommendation.system.menu.MenuTabs;
import event.recommendation.system.repositories.EventRepository;
import event.recommendation.system.services.event.strategy.*;
import event.recommendation.system.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;

import static event.recommendation.system.enums.EventType.NONE;

/* TODO: refactor*/
@Service
@RequiredArgsConstructor
public class EventService {
    private final UserManager userManager = new UserManager();
    private final EventRepository eventRepository;
    private final EventValidator eventValidator;
    private final EventAdapter eventAdapter;
    private final UserService userService;

    public void addNewEvent(String title, String from, String to, Date date, String eventType, EventSpace eventSpace, User user, Model model) {
        Event event = eventAdapter.adapt(title, from, to, date, eventType, eventSpace, user);
        if (eventValidator.isValid(event, model)) {
            saveWithEventSpace(event);
        }
    }

    public Event saveWithEventSpace(Event event) {
        return eventRepository.save(event);
    }

    public void addEventsModelAttributes(String eventType, Model model) {
        Map<String, List<Event>> events = getWeekEvents(eventType);
        List<Event> availableEvents = filterEventsAvailableToRegistration(events, userManager.getLoggedInUser());
        model.addAttribute("menuElements", MenuTabs.getInstance().getDefaultMenu());
        model.addAttribute("slideMenuElements", MenuTabs.getInstance().getDefaultSlideMenu());
        model.addAttribute("eventTypes", EventType.getEventsTypes());
        model.addAttribute("events", events);
        model.addAttribute("availableEvents", availableEvents);
        model.addAttribute("user", userManager.getLoggedInUser());
        model.addAttribute("isUserAdmin", userManager.isLoggedUserAdmin());
    }

    private List<Event> filterEventsAvailableToRegistration(Map<String, List<Event>> events, User user) {
        return filterEventsAvailableToRegistration(getAllEvents(events), user);
    }

    private List<Event> getAllEvents(Map<String, List<Event>> events) {
        List<Event> allEvents = new ArrayList<>();
        for (List<Event> events1: events.values()) {
            allEvents.addAll(events1);
        }
        return allEvents;
    }

    private List<Event> filterEventsAvailableToRegistration(List<Event> events, User user){
        List<Event> availableEvents = new ArrayList<>(events);
        for (Event event : events) {
            for (Event userEvent : user.getEvents()) {
                if(event.getId().equals(userEvent.getId())) {
                    availableEvents.remove(event);
                }
            }
        }
        return availableEvents;
    }

    public Map<String, List<Event>> getWeekEvents(String eventType) {
        EventType type = StringUtils.isBlank(eventType) ? NONE : EventType.valueOf(eventType);
        return getWeekEvents(getEventsService(type));
    }

    private Map<String, List<Event>> getWeekEvents(EventsService strategyEventService) {
        return strategyEventService.filterEvents();
    }

    private EventsService getEventsService(EventType eventType) {
        switch (eventType) {
            case SPORT:
                return new SportEventService(new DefaultEventService(eventRepository));
            case ART:
                return new ArtEventService(new DefaultEventService(eventRepository));
            case EDUCATION:
                return new EducationEventService(new DefaultEventService(eventRepository));
            case NONE:
            default:
                return new DefaultEventService(eventRepository);
        }
    }

    public void deleteEvent(Event event) {
        event.setActive(false);
        eventRepository.save(event);
    }

    public void cancelRegistration(User user, Event event) {
        user.getEvents().removeIf(userEvent -> userEvent.getId().equals(event.getId()));
        event.getParticipants().removeIf(usr -> usr.getId().equals(user.getId()));
        userService.save(user);
        eventRepository.save(event);
    }

    public String addingEventResultRedirection(Model model, String eventType) {
        if (MapUtils.isNotEmpty(model.asMap())) {
            addEventsModelAttributes(eventType, model);
        }
        return "redirect:/events/creation";
    }

    public void registerUserForEvent(User user, Event event) {
        user.getEvents().add(event);
        userService.save(user);
    }

    public List<Event> getAllFromToday() {
        return eventRepository.getByDateAfter(new Date())
                .orElseGet(ArrayList::new);
    }

    public List<Event> getByTagsAndFrom(Set<Tag> tags) {
        return eventRepository.getByTagsInAndDateAfter(tags, new Date())
                .orElseGet(ArrayList::new);
    }

    public List<Event> getAll() {
        return (List<Event>) eventRepository.findAll();
    }
}
