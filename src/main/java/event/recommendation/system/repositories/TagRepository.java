package event.recommendation.system.repositories;

import event.recommendation.system.entities.Tag;
import event.recommendation.system.enums.EventType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Set;

public interface TagRepository extends CrudRepository<Tag, Long> {
    Set<Tag> findAll();
    Set<Tag> findAllByType(EventType type);
    Set<Tag> findAllByTypeIn(Collection<EventType> types);
}
