package event.recommendation.system.controllers.event;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.EventSpace;
import event.recommendation.system.entities.User;
import event.recommendation.system.services.event.EventService;
import event.recommendation.system.services.event.strategy.EventsMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/events/creation")
@RequiredArgsConstructor
public class EventCreationController {
    private final EventService eventService;
    private final EventsMainService eventsMainService;

    @GetMapping
    public String eventCreation(@RequestParam(required = false, defaultValue = "")
                                        String eventType, Model model) {
        eventService.addEventsModelAttributes(eventType, model);
        eventsMainService.addEventsAttributes(model);
        eventsMainService.addCreatedAndDeletedEvents(model);
        return "events_creation";
    }

    @PostMapping("/add")
    public String add(EventSpace eventSpace,
                      @RequestParam String title,
                      @RequestParam String from,
                      @RequestParam String to,
                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                      @RequestParam String eventType,
                      @AuthenticationPrincipal User user,
                      Model model) {
        eventService.addNewEvent(title, from, to, date, eventType, eventSpace, user, model);
        return eventService.addingEventResultRedirection(model, eventType);
    }

    @PostMapping("/delete")
    public String delete(Event event) {
        eventService.deleteEvent(event);
        return "redirect:/events/creation#created-events";
    }

    @PostMapping("/renew")
    public String renew(Event event) {
        eventService.renew(event);
        return "redirect:/events/creation#created-events";
    }
}
