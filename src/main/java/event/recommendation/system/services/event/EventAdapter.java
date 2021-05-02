package event.recommendation.system.services.event;

import event.recommendation.system.date.TimeParser;
import event.recommendation.system.entities.event.Event;
import event.recommendation.system.entities.user.User;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.models.DayOfWeek;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class EventAdapter {

    public Event adapt(String title, String from, String to, Date date, String eventType, User user) {
        LocalTime fromParsed = TimeParser.parseToLocalTime(from).orElseThrow(IllegalArgumentException::new);
        LocalTime toParsed = TimeParser.parseToLocalTime(to).orElseThrow(IllegalArgumentException::new);
        DayOfWeek dayOfWeek = getDayOfWeekByDayNumber(date.getDay());
        return buildEvent(title, fromParsed, toParsed, date, dayOfWeek, EventType.valueOf(eventType), user);
    }

    public DayOfWeek getDayOfWeekByDayNumber(int dayNumber) {
        return DayOfWeek.values()[dayNumber];
    }

    public Event buildEvent(String title, LocalTime from, LocalTime to, Date date, DayOfWeek dayOfWeek, EventType eventType, User creator) {
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
