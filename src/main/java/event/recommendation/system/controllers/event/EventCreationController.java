package event.recommendation.system.controllers.event;

import event.recommendation.system.dto.EventDTO;
import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.EventSpace;
import event.recommendation.system.entities.User;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.services.controllers.EventCreationControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/events/creation")
@RequiredArgsConstructor
public class EventCreationController {
   private final EventCreationControllerService creationControllerService;

    @GetMapping
    public String eventCreation(@RequestParam(required = false, defaultValue = "")
                                        EventType eventType, Model model) {
        creationControllerService.onEventCreationView(model, eventType);
        return "events_creation";
    }

    @PostMapping("/add")
    public String add(@RequestParam Map<String, String> form,
                      EventSpace eventSpace,
                      EventDTO eventDTO,
                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                      @AuthenticationPrincipal User user,
                      Model model) {
        creationControllerService.onEventCreation(model, eventSpace, eventDTO, user, form);
        return "redirect:/events/creation";
    }

    @PostMapping("/delete")
    public String delete(Event event) {
        creationControllerService.onDelete(event);
        return "redirect:/events/creation#created-events";
    }

    @PostMapping("/renew")
    public String renew(Event event) {
        creationControllerService.onRenew(event);
        return "redirect:/events/creation#created-events";
    }
}
