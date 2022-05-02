package event.recommendation.system.services.event.strategy;


import event.recommendation.system.entities.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static event.recommendation.system.enums.EventType.ART;

@Service
@RequiredArgsConstructor
public class ArtEventService implements EventsService {
    private final DefaultUserEventService defaultUserEventService;

    @Override
    public Map<String, List<Event>> filterEvents() {
        return getArtEvents();
    }

    private Map<String, List<Event>> getArtEvents() {
        Map<String, List<Event>> events = defaultUserEventService.getSortedEventsForFullWeekFromTodayWithDay();
        events.values()
                .forEach(eventsList -> eventsList.removeIf(event -> !isArtEvent(event)));
        return events;
    }

    private boolean isArtEvent(Event event) {
        return Objects.equals(event.getType(), ART);
    }
}
