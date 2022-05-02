package event.recommendation.system.controllers.event;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.EventRating;
import event.recommendation.system.entities.User;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.services.controllers.EventsMainControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventsMainController {
    private final EventsMainControllerService eventsMainControllerService;

    /*TODO - filtering*/
    @GetMapping
    public String events(@RequestParam(required = false, defaultValue = "")
                                     EventType eventType, Model model) {
        eventsMainControllerService.onEvents(model);
        return "events";
    }

    @PostMapping("/register")
    public String registerUserOnEvent(User user, Event event, Model model) {
        eventsMainControllerService.onRegisterUserOnEvent(user, event, model);
        return "redirect:/events#events";
    }

    @PostMapping("/rate")
    public String userEditedSave(EventRating eventRating) {
        eventsMainControllerService.onRate(eventRating);
        return "redirect:/events#events";
    }
}
