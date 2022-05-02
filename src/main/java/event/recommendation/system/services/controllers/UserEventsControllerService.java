package event.recommendation.system.services.controllers;

import event.recommendation.system.entities.Event;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.services.builders.ModelBuilder;
import event.recommendation.system.services.event.UserEventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class UserEventsControllerService {
    private final ModelBuilder modelBuilder;
    private final UserEventsService userEventsService;

    public void onUserEvents(Model model, EventType eventType) {
        modelBuilder.builder(model)
                .withUserCalendarEvents(eventType)
                .withUserOutOfDateEvents()
                .withLoggedUser()
                .withDefaultMenu();
    }

    public void onCancelRegistration(Event event) {
        userEventsService.cancelRegistration(event);
    }

}
