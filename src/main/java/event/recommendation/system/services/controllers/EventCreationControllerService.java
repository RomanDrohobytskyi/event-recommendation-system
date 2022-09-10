package event.recommendation.system.services.controllers;

import event.recommendation.system.dto.EventDTO;
import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.EventSpace;
import event.recommendation.system.entities.Tag;
import event.recommendation.system.entities.User;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.services.builders.ModelBuilder;
import event.recommendation.system.services.event.EventService;
import event.recommendation.system.services.tag.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Map;
import java.util.Set;

import static org.apache.commons.collections.MapUtils.isNotEmpty;

@Service
@RequiredArgsConstructor
public class EventCreationControllerService {
    private final EventService eventService;
    private final ModelBuilder modelBuilder;
    private final TagService tagService;

    public void onEventCreationView(Model model, EventType eventType) {
        eventService.addEventsModelAttributes(eventType, model);

        modelBuilder.builder(model)
                .withDefaultMenu()
                .withEventTypes()
                .withLoggedUser()
                .withCreatedEventsByUser()
                .withDeletedEventsByUser()
                .withAllTags();
    }

    public void onEventCreation(Model model, EventSpace eventSpace, EventDTO eventDTO, User user, Map<String, String> form) {
        Set<Tag> eventTags = tagService.getTagsFromForm(form);
        eventService.addNewEvent(eventDTO, eventSpace, user, eventTags);

        if (isNotEmpty(model.asMap())) {
            modelBuilder.builder(model)
                    .withDefaultMenu()
                    .withEventTypes()
                    .withLoggedUser();
        }
    }

    public void onDelete(Event event) {
        eventService.deleteEvent(event);
    }

    public void onRenew(Event event) {
        eventService.renew(event);
    }
}
