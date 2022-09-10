package event.recommendation.system.services.event;

import event.recommendation.system.dto.EventDTO;
import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.EventSpace;
import event.recommendation.system.entities.Tag;
import event.recommendation.system.entities.User;
import event.recommendation.system.models.DayOfWeek;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

import static event.recommendation.system.services.event.util.EventAdapterUtil.getDayOfWeekByNumber;

@Service
@RequiredArgsConstructor
public class EventAdapter {

    public Event adapt(EventDTO eventDTO, EventSpace eventSpace, User user, Set<Tag> tags) {
        DayOfWeek dayOfWeek = getDayOfWeekByNumber(eventDTO.getDate().getDayOfWeek().getValue());
        Event event = Event.builder()
                .title(eventDTO.getTitle())
                .description(eventDTO.getDescription())
                .from(eventDTO.getFrom())
                .to(eventDTO.getTo())
                .date(eventDTO.getDate())
                .dayOfWeek(dayOfWeek)
                .creator(user)
                .creationDate(new Date())
                .type(eventDTO.getType())
                .tags(tags)
                .space(eventSpace)
                .build();
        eventSpace.setEvent(event);
        return event;
    }

}
