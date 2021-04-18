package event.recommendation.system.services.event.strategy;

import event.recommendation.system.entities.event.Event;
import event.recommendation.system.entities.user.User;
import event.recommendation.system.managers.UserManager;
import event.recommendation.system.services.event.EventService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventsMainService {
    private final EventService eventService;
    private final UserManager userManager = new UserManager();

    public void addEventsAttributes(Model model) {
        List<Event> userPreferencesEvents = getEventsForUser(userManager.getLoggedInUser());
        List<Event> otherEvents = getOtherEvents(userPreferencesEvents);
        model.addAttribute("userPreferencesEvents", userPreferencesEvents);
        model.addAttribute("otherEvents", otherEvents);
    }

    private List<Event> getEventsForUser(User user) {
        if(CollectionUtils.isNotEmpty(user.getTags())) {
            return eventService.getByTagsAndFrom(user.getTags());
        } else {
            //TODO: return some popular/ high rating events
            return Collections.emptyList();
        }
    }

    private List<Event> getOtherEvents(List<Event> userPreferencesEvents) {
        List<Event> otherEvents = getAllEventsFromToday();
        otherEvents.removeIf(userPreferencesEvents::contains);
        return otherEvents;
    }

    private List<Event> getAllEventsFromToday() {
        return eventService.getAllFromToday();
    }
}
