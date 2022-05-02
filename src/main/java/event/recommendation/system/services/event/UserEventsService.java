package event.recommendation.system.services.event;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.User;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.managers.UserManager;
import event.recommendation.system.repositories.UserEventsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static event.recommendation.system.menu.MenuTabs.defaultMenu;
import static java.time.LocalTime.now;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserEventsService {
    private final EventService eventService;
    private final UserEventsRepository userEventsRepository;
    private final UserManager userManager = new UserManager();

    /*
    * TODO
    * Model builder !!!!!!!!
    * model.withLoggedInUser().withUserOutOfDateEvents() etc!!!!!!!
    * */
    public void getUserEventsAndModel(EventType eventType, Model model) {
        User user = userManager.getLoggedInUser();
        model.addAttribute("user", userManager.getLoggedInUser());
        model.addAttribute("outOfDateEvents", getOutOfDateEvents(user));
        defaultMenu(model);
    }


    private List<Event> getActualAndActiveUserEvents(User user) {
        return user.getEvents().stream()
                .filter(this::isActual)
                .collect(toList());
    }

    private boolean isActual(Event event) {
        return event.isActive()
                && event.getDate().equals(LocalDate.now())
                && event.getTo().isAfter(now());
    }

    public List<Event> getOutOfDateEvents(User user) {
        return user.getEvents().stream()
                .filter(this::isNotActual)
                .collect(toList());
    }

    private boolean isNotActual(Event event) {
        return event.isActive()
                || (event.getDate().isBefore(LocalDate.now()) || event.getDate().equals(LocalDate.now()))
                && event.getTo().isBefore(now());
    }

    public void cancelRegistration(Event event) {
        eventService.cancelRegistration(userManager.getLoggedInUser(), event);
    }

    public Optional<List<Event>> getEventsByParticipantAndDateAfter(User user, LocalDate startDate) {
        return userEventsRepository.getEventsByParticipantsInAndDateAfter(Set.of(user), startDate);
    }
}
