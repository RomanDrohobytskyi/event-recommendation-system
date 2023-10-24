package event.recommendation.system.model;

import event.recommendation.system.enums.EventType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.DayOfWeek;
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
    private String description;
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
    private UserDTO creator;
    private Set<UserDTO> participants;
    private EventType type;
    @Builder.Default
    private Set<TagDTO> tags = new HashSet<>();
    private EventSpace space;
    private Set<EventRating> rates;
}
