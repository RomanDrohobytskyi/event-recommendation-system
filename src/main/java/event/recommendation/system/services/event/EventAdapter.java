package event.recommendation.system.services.event;

import event.recommendation.system.date.TimeParser;
import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.EventSpace;
import event.recommendation.system.entities.User;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.models.DayOfWeek;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class EventAdapter {

    public Event adapt(String title, String from, String to, Date date, String eventType, EventSpace eventSpace, User user) {
        Event event = adapt(title, from, to, date, eventType, user);
        event.setSpace(eventSpace);
        eventSpace.setEvent(event);
        return event;
    }

    public Event adapt(String title, String from, String to, Date date, String eventType, User user) {
        LocalTime fromParsed = TimeParser.parseToLocalTime(from).orElseThrow(IllegalArgumentException::new);
        LocalTime toParsed = TimeParser.parseToLocalTime(to).orElseThrow(IllegalArgumentException::new);
        DayOfWeek dayOfWeek = getDayOfWeekByDayNumber(date.getDay());
        return build(title, fromParsed, toParsed, date, dayOfWeek, EventType.valueOf(eventType), user);
    }

    private DayOfWeek getDayOfWeekByDayNumber(int dayNumber) {
        return DayOfWeek.values()[dayNumber];
    }

    private Event build(String title, LocalTime from, LocalTime to, Date date, DayOfWeek dayOfWeek, EventType eventType, User creator) {
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

    private EventSpace build(String country, String city, String address, String zipCode, Event event) {
        return EventSpace.builder()
                .country(country)
                .city(city)
                .address(address)
                .zipCode(zipCode)
                .event(event)
                .build();
    }

}
