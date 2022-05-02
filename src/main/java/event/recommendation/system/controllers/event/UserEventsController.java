package event.recommendation.system.controllers.event;

import event.recommendation.system.entities.Event;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.services.controllers.UserEventsControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/events/user")
@RequiredArgsConstructor
public class UserEventsController {
    private final UserEventsControllerService userEventsControllerService;

    @GetMapping
    public String userEvents(@RequestParam(required = false) EventType eventType, Model model) {
        userEventsControllerService.onUserEvents(model, eventType);
        return "user_events";
    }

    @PostMapping("/discard")
    public String discard(Event event) {
        userEventsControllerService.onCancelRegistration(event);
        return "redirect:/events/user";
    }
}
