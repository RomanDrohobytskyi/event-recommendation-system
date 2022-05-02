package event.recommendation.system.repositories;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserEventsRepository extends CrudRepository<Event, Long> {
    Optional<List<Event>> getEventsByParticipantsInAndDateAfter(Set<User> participants, @NotNull LocalDate date);
}
