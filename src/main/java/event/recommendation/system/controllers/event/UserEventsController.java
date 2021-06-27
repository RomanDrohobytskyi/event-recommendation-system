package event.recommendation.system.controllers.event;

import event.recommendation.system.entities.Event;
import event.recommendation.system.services.event.UserEventsService;
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
    private final UserEventsService userEventsService;

    @GetMapping
    public String userEvents(@RequestParam(required = false, defaultValue = "")
                                     String eventType, Model model) {
        userEventsService.getUserEventsAndModel(eventType, model);
        return "user_events";
    }

    @PostMapping("/discard")
    public String discard(Event event) {
        userEventsService.cancelRegistration(event);
        return "redirect:/events/user";
    }
}
