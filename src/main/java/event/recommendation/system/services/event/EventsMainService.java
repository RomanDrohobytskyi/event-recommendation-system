package event.recommendation.system.services.event;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.User;
import event.recommendation.system.managers.UserManager;
import event.recommendation.system.services.recommendation.algorithm.EventRecommendationAlgorithmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

import static java.time.LocalDate.now;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EventsMainService {
    private final EventService eventService;
    private final EventRecommendationAlgorithmService eventRecommendationAlgorithmService;
    private final UserManager userManager = new UserManager();

    public List<Event> getEventsForUser(User user) {
        return eventRecommendationAlgorithmService.getRecommendedEventsForUser(user);
    }

    public List<Event> getOtherEvents(List<Event> userPreferencesEvents) {
        List<Event> otherEvents = getAllEventsFromToday();
        otherEvents.removeIf(userPreferencesEvents::contains);
        return otherEvents;
    }

    private List<Event> getAllEventsFromToday() {
        return eventService.getAllFromToday();
    }

    public void addCreatedAndDeletedEvents(Model model) {

    }

    public List<Event> getCreatedActiveEvents(User user) {
        return eventService.getByCreatorAndDateEqualsOrDateAfter(user, now()).stream()
                .filter(Event::isActive)
                .collect(toList());
    }

    private boolean isActual(Event event) {
        return event.getDate().isEqual(now()) || event.getDate().isAfter(now());
    }

    public List<Event> getDeletedNotActiveEvents(User user) {
        return user.getCreatedEvents().stream()
                .filter(this :: isOutOfDate)
                .collect(toList());
    }

    private boolean isOutOfDate(Event event) {
        return !event.isActive() || event.getDate().isBefore(now());
    }
}