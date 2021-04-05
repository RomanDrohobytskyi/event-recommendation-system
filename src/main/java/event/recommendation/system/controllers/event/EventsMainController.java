package event.recommendation.system.controllers.event;

import event.recommendation.system.entities.event.Event;
import event.recommendation.system.entities.user.User;
import event.recommendation.system.services.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventsMainController {

    private final EventService eventService;

    @GetMapping
    public String events(@RequestParam(required = false, defaultValue = "")
                                     String eventType, Model model) {
        eventService.addEventsModelAttributes(eventType, model);
        return "events";
    }

    @PostMapping("/add")
    public String add(@RequestParam String title,
                      @RequestParam String from,
                      @RequestParam String to,
                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                      @RequestParam String eventType,
                      @AuthenticationPrincipal User user,
                      Model model) {
        eventService.addNewEvent(title, from, to, date, eventType, user, model);
        return eventService.addingEventResultRedirection(model, eventType);
    }

    @DeleteMapping("/delete/{event}")
    public String delete(@PathVariable Event event){
        eventService.deleteEvent(event);
        return "redirect:/events";
    }

}
