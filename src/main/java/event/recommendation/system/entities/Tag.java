package event.recommendation.system.entities;

import event.recommendation.system.common.BaseEntity;
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
public class Tag extends BaseEntity {
    @Column(unique = true)
    private String name;
    private String description;
    @NotNull
    @Column(name="event_type")
    @Enumerated(EnumType.STRING)
    private EventType type;
    @ManyToMany(mappedBy = "tags", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Event> event;
}
