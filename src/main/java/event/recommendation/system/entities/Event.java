package event.recommendation.system.entities;

import event.recommendation.system.common.BaseEntity;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.models.DayOfWeek;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event extends BaseEntity {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    @Column(name = "starts_from")
    private LocalTime from;
    @NotNull
    @Column(name = "end_to")
    private LocalTime to;
    @NotNull
    private LocalDate date;
    @NotNull
    @Builder.Default
    private boolean active = true;
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;
    @Column(name = "creation_date")
    @NotNull
    private Date creationDate;
    @Column(name = "modification_date")
    private Date modificationDate;
    @ManyToOne
    private User creator;
    @ManyToMany(mappedBy = "events", fetch = FetchType.LAZY)
    private Set<User> participants;
    @Enumerated(EnumType.STRING)
    @NotNull
    private EventType type;
    @NotNull
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "event_tags",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private Set<Tag> tags = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL)
    private EventSpace space;
    @OneToMany(mappedBy = "evaluatedEvent", fetch = FetchType.EAGER)
    private Set<EventRating> rates;

    public Double getAverageScore() {
        return rates.stream()
                .mapToDouble(EventRating::getScore)
                .average()
                .orElse(0.0);
    }

}
