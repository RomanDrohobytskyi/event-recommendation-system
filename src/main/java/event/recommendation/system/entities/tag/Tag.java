package event.recommendation.system.entities.tag;

import event.recommendation.system.entities.event.Event;
import event.recommendation.system.enums.EventType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    @Column(name="event_type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private EventType type;
    @ManyToMany(mappedBy = "tags", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Event> event;
}
