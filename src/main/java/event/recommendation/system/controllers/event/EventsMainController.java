package event.recommendation.system.controllers.event;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.EventRating;
import event.recommendation.system.entities.User;
import event.recommendation.system.services.event.EventRatingService;
import event.recommendation.system.services.event.EventService;
import event.recommendation.system.services.event.strategy.EventsMainService;
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
    private final EventService eventService;
    private final EventsMainService eventsMainService;
    private final EventRatingService eventRatingService;

    @GetMapping
    public String events(@RequestParam(required = false, defaultValue = "")
                                     String eventType, Model model) {
        eventService.addEventsModelAttributes(eventType, model);
        eventsMainService.addEventsAttributes(model);
        return "events";
    }

    @PostMapping("/register")
    public String addUser(User user, Event event, Model model) {
        eventService.registerUserForEvent(user, event);
        eventService.addEventsModelAttributes(null, model);
        return "redirect:/events#events";
    }

    @PostMapping("/rate")
    public String userEditedSave(EventRating eventRating) {
        eventRatingService.rate(eventRating);
        return "redirect:/events#events";
    }
}
