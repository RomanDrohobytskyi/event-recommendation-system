package event.recommendation.system.repositories;

import event.recommendation.system.entities.EventSpace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventSpaceRepository  extends CrudRepository<EventSpace, Long>  {
}
