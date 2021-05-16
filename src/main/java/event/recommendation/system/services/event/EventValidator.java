package event.recommendation.system.services.event;

import event.recommendation.system.entities.Event;
import event.recommendation.system.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class EventValidator {
    private final EventRepository eventRepository;

    public boolean isValid(Event event, Model model) {
        if (validateAvailableHours(event)) {
            model.addAttribute("message", "This time is already taken for another event!");
            return false;
        }
        return true;
    }

    public boolean validateAvailableHours(Event event) {
        return eventRepository.getFirstByFromBeforeAndToBeforeAndDayOfWeekAndDate(
                event.getFrom(), event.getTo(), event.getDayOfWeek(), event.getDate())
                .isPresent();
    }

}
