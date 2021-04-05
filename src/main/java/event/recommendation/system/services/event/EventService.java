package event.recommendation.system.services.event;

import event.recommendation.system.date.TimeParser;
import event.recommendation.system.entities.event.Event;
import event.recommendation.system.entities.user.User;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.menu.MenuTabs;
import event.recommendation.system.models.DayOfWeek;
import event.recommendation.system.repositories.EventRepository;
import event.recommendation.system.services.event.strategy.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static event.recommendation.system.enums.EventType.NONE;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private Model model;

    public Event addNewEvent(String title, String from, String to, Date date, String eventType, User user, Model model) {
        this.model = model;
        Event event = adaptEvent(title, from, to, date, eventType, user);
        return validateEvent(event) ? save(event) : event;
    }

    public boolean validateEvent(Event event) {
        if (!validateAvailableHours(event)) {
            model.addAttribute("message", "This time is already taken for another event!");
            return false;
        }
        return true;
    }

    public boolean validateAvailableHours(Event event) {
        return eventRepository.getFirstByFromBeforeAndToBeforeAndDayOfWeekAndDate(
                event.getFrom(), event.getTo(), event.getDayOfWeek(), event.getDate())
                .isPresent();
    }

    public Event adaptEvent(String title, String from, String to, Date date, String eventType, User user) {
        LocalTime fromParsed = TimeParser.parseToLocalTime(from).orElseThrow(IllegalArgumentException::new);
        LocalTime toParsed = TimeParser.parseToLocalTime(to).orElseThrow(IllegalArgumentException::new);
        DayOfWeek dayOfWeek = getDayOfWeekByDayNumber(date.getDay());
        return buildEvent(title, fromParsed, toParsed, date, dayOfWeek, EventType.valueOf(eventType), user);
    }

    public DayOfWeek getDayOfWeekByDayNumber(int dayNumber) {
        return DayOfWeek.values()[dayNumber];
    }

    public Event buildEvent(String title, LocalTime from, LocalTime to, Date date, DayOfWeek dayOfWeek, EventType eventType, User user) {
        return Event.builder()
                .title(title)
                .from(from)
                .to(to)
                .date(date)
                .dayOfWeek(dayOfWeek)
                .user(user)
                .creationDate(new Date())
                .type(eventType)
                .build();
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public void addEventsModelAttributes(String eventType, Model model) {
        model.addAttribute("menuElements", MenuTabs.defaultMenu());
        model.addAttribute("slideMenuElements", MenuTabs.defaultSlideMenu());
        model.addAttribute("eventTypes", EventType.getEventsTypes());
        model.addAttribute("events", getWeekEvents(eventType));
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
        eventRepository.delete(event);
    }

    public String addingEventResultRedirection(Model model, String eventType) {
        if (model.asMap().isEmpty()) {
            return "redirect:/events";
        } else {
            addEventsModelAttributes(eventType, model);
            return "events";
        }
    }
}
