package event.recommendation.system.services.event.strategy;

import event.recommendation.system.entities.event.Event;

import java.util.List;
import java.util.Map;

public interface EventsService {
    Map<String, List<Event>> filterEvents();
}
