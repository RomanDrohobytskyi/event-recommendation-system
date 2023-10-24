package event.recommendation.system.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class EventRating {
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
