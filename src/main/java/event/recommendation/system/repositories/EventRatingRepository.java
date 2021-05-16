package event.recommendation.system.repositories;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.EventRating;
import event.recommendation.system.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRatingRepository extends CrudRepository<EventRating, Long>  {
    Optional<EventRating> getByEvaluatorAndEvaluatedEvent(User evaluator, Event evaluatedEvent);
}
