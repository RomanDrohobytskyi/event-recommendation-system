package event.recommendation.system.model;

import event.recommendation.system.enums.EventType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class TagDTO {
    private Long id;
    private String name;
    private String description;
    private EventType type;
    private Set<Event> event;
}
