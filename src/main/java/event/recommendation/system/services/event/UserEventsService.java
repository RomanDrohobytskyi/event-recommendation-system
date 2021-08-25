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

import static event.recommendation.system.date.DateParser.getCurrentDateWithoutTime;
import static java.time.LocalTime.now;
import static java.util.stream.Collectors.toList;

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
        return user.getEvents().stream()
                .filter(this::isActual)
                .collect(toList());
    }

    private boolean isActual(Event event) {
        Date currentDateWithoutTime = getCurrentDateWithoutTime();
        return event.isActive()
                && event.getDate().equals(currentDateWithoutTime)
                && event.getTo().isAfter(now());
    }

    private List<Event> getOutOfDateEvents(User user) {
        return user.getEvents().stream()
                .filter(this::isNotActual)
                .collect(toList());
    }

    private boolean isNotActual(Event event) {
        Date currentDateWithoutTime = getCurrentDateWithoutTime();
        return event.isActive()
                || (event.getDate().before(currentDateWithoutTime) || event.getDate().equals(currentDateWithoutTime))
                && event.getTo().isBefore(now());
    }

    public void cancelRegistration(Event event) {
        eventService.cancelRegistration(userManager.getLoggedInUser(), event);
    }
}
