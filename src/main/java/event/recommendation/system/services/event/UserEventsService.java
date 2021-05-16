package event.recommendation.system.services.event;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.User;
import event.recommendation.system.managers.UserManager;
import event.recommendation.system.menu.MenuTabs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserEventsService {
    private final EventService eventService;
    private final UserManager userManager = new UserManager();

    public void getUserEventsAndModel(String eventType, Model model) {
        User user = userManager.getLoggedInUser();
        model.addAttribute("user", userManager.getLoggedInUser());
        model.addAttribute("actualEvents", getActualAndActiveUserEvents(user));
        model.addAttribute("outOfDateEvents", getOutOfDateEvents(user));
        model.addAttribute("menuElements", MenuTabs.getInstance().getDefaultMenu());
        model.addAttribute("slideMenuElements", MenuTabs.getInstance().getDefaultSlideMenu());
    }

    private List<Event> getActualAndActiveUserEvents(User user) {
        Date now = new Date();
        return user.getEvents().stream()
                .filter(Event::isActive)
                .filter(event -> event.getDate().after(now))
                .collect(Collectors.toList());
    }

    private List<Event> getOutOfDateEvents(User user) {
        Date now = new Date();
        return user.getEvents().stream()
                .filter(event -> event.getDate().before(now))
                .collect(Collectors.toList());
    }

    public void cancelRegistration(Event event) {
        eventService.cancelRegistration(userManager.getLoggedInUser(), event);
    }
}
