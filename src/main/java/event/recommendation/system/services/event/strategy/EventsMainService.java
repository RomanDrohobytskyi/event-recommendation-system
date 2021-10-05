package event.recommendation.system.services.event.strategy;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.User;
import event.recommendation.system.managers.UserManager;
import event.recommendation.system.services.event.EventService;
import event.recommendation.system.services.recommendation.algorithm.EventRecommendationAlgorithmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EventsMainService {
    private final EventService eventService;
    private final EventRecommendationAlgorithmService eventRecommendationAlgorithmService;
    private final UserManager userManager = new UserManager();

    public void addEventsAttributes(Model model) {
        User loggedInUser = userManager.getLoggedInUser();
        List<Event> userPreferencesEvents = getEventsForUser(loggedInUser);
        List<Event> allEvents = getOtherEvents(userPreferencesEvents);
        model.addAttribute("userPreferencesEvents", userPreferencesEvents);
        model.addAttribute("otherEvents", allEvents);
    }

    public void addCreatedAndDeletedEvents(Model model) {
        User loggedInUser = userManager.getLoggedInUser();
        List<Event> createdActiveEvents = getCreatedActiveEvents(loggedInUser);
        List<Event> deletedNotActiveEvents = getDeletedNotActiveEvents(loggedInUser);
        model.addAttribute("createdActiveEvents", createdActiveEvents);
        model.addAttribute("deletedNotActiveEvents", deletedNotActiveEvents);
    }

    private List<Event> getEventsForUser(User user) {
       return eventRecommendationAlgorithmService.getRecommendedEventsForUser(user);
    }

    //TODO: verify!
    private List<Event> getOtherEvents(List<Event> userPreferencesEvents) {
        List<Event> otherEvents = getAllEventsFromToday();
        otherEvents.removeIf(userPreferencesEvents::contains);
        return otherEvents;
    }

    private List<Event> getAllEventsFromToday() {
        return eventService.getAllFromToday();
    }

    private List<Event> getCreatedActiveEvents(User user) {
        return user.getCreatedEvents().stream()
                .filter(Event::isActive)
                .collect(toList());
    }

    private List<Event> getDeletedNotActiveEvents(User user) {
        return user.getCreatedEvents().stream()
                .filter(event -> !event.isActive())
                .collect(toList());
    }
}