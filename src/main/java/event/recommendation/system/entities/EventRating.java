package event.recommendation.system.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(1)
    @Max(5)
    @NotNull
    private int score;
    @NotNull
    @ManyToOne
    private User evaluator;
    @NotNull
    @ManyToOne
    private Event evaluatedEvent;

}
