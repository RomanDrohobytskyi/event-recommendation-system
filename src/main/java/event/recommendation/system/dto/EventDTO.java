package event.recommendation.system.dto;

import event.recommendation.system.entities.EventRating;
import event.recommendation.system.entities.EventSpace;
import event.recommendation.system.entities.Tag;
import event.recommendation.system.entities.User;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.models.DayOfWeek;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventDTO {
    private Long id;
    private String title;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime from;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime to;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    @Builder.Default
    private boolean active = true;
    private DayOfWeek dayOfWeek;
    private Date creationDate;
    private Date modificationDate;
    private User creator;
    private Set<User> participants;
    private EventType type;
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();
    private EventSpace space;
    private Set<EventRating> rates;
}
