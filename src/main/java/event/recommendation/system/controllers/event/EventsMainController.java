package event.recommendation.system.controllers.event;

import event.recommendation.system.entities.event.Event;
import event.recommendation.system.services.event.EventService;
import event.recommendation.system.services.event.strategy.EventsMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventsMainController {
    private final EventService eventService;
    private final EventsMainService eventsMainService;

    @GetMapping
    public String events(@RequestParam(required = false, defaultValue = "")
                                     String eventType, Model model) {
        eventService.addEventsModelAttributes(eventType, model);
        eventsMainService.addEventsAttributes(model);
        return "events";
    }

    @GetMapping("/register/{event}")
    public String registerForEvent(@PathVariable Event event,
                                   Model model) {
        eventService.registerUserForEvent(event);
        eventService.addEventsModelAttributes(null, model);
        return "redirect:/events#events";
    }
}
