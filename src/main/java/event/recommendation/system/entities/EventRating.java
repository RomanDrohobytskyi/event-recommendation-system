package event.recommendation.system.entities;

import event.recommendation.system.common.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRating extends BaseEntity {
    @Min(1)
    @Max(5)
    @NotNull
    private int score;
    @NotNull
    private LocalDateTime date;
    @NotNull
    @ManyToOne
    private User evaluator;
    @NotNull
    @ManyToOne
    private Event evaluatedEvent;
}
