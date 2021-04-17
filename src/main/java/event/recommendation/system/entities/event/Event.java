package event.recommendation.system.entities.event;

import event.recommendation.system.entities.tag.Tag;
import event.recommendation.system.entities.user.User;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.models.DayOfWeek;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @NotNull
    @Column(name = "starts_from")
    private LocalTime from;
    @NotNull
    @Column(name = "end_to")
    private LocalTime to;
    @NotNull
    private Date date;
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
    @ManyToMany(mappedBy = "events", fetch = FetchType.EAGER)
    private Set<User> participants;
    @Enumerated(EnumType.STRING)
    @NotNull
    private EventType type;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "event_tags",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private Set<Tag> tags = new HashSet<>();
    @OneToOne
    private EventSpace space;
}
