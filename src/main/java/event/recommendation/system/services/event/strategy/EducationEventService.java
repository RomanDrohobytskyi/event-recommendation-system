package event.recommendation.system.services.event.strategy;

import event.recommendation.system.entities.event.Event;
import event.recommendation.system.enums.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EducationEventService implements EventsService {

    private final DefaultEventService defaultEventService;

    @Override
    public Map<String, List<Event>> filterEvents() {
        return getEducationEvents();
    }

    private Map<String, List<Event>> getEducationEvents() {
        Map<String, List<Event>> events = defaultEventService.getSortedEventsForFullWeekFromTodayWithDay();
        events.values()
                .forEach(eventsList -> eventsList.removeIf(event -> !isEducationEvent(event)));
        return events;
    }

    private boolean isEducationEvent(Event event) {
        return event.getType().equals(EventType.EDUCATION);
    }

}
