package event.recommendation.system.services.event;

import event.recommendation.system.dto.EventDTO;
import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.EventSpace;
import event.recommendation.system.entities.User;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.models.DayOfWeek;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class EventAdapter {

    public Event adapt(EventDTO eventDTO, EventSpace eventSpace, User user) {
        Event event = adapt(eventDTO.getTitle(), eventDTO.getFrom(), eventDTO.getTo(), eventDTO.getDate(), eventDTO.getType(), user);
        event.setSpace(eventSpace);
        eventSpace.setEvent(event);
        return event;
    }

    public Event adapt(String title, LocalTime from, LocalTime to, LocalDate date, EventType eventType, User user) {
        DayOfWeek dayOfWeek = getDayOfWeekByDayNumber(date.getDayOfWeek().getValue());
        return build(title, from, to, date, dayOfWeek, eventType, user);
    }

    private DayOfWeek getDayOfWeekByDayNumber(int dayNumber) {
        return DayOfWeek.values()[dayNumber - 1];
    }

    private Event build(String title, LocalTime from, LocalTime to, LocalDate date, DayOfWeek dayOfWeek, EventType eventType, User creator) {
        return Event.builder()
                .title(title)
                .from(from)
                .to(to)
                .date(date)
                .dayOfWeek(dayOfWeek)
                .creator(creator)
                .creationDate(new Date())
                .type(eventType)
                .build();
    }

}
