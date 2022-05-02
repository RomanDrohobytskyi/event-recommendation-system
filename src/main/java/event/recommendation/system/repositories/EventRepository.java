package event.recommendation.system.repositories;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.Tag;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.models.DayOfWeek;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    @Query(value = "SELECT * FROM event WHERE date >= CAST(?1 AS DATE)  AND date <= CAST(?2 AS DATE)", nativeQuery = true)
    Optional<List<Event>> getEventsForDateRage(LocalDate from, LocalDate to);
    Optional<Event> getFirstByFromBeforeAndToBeforeAndDayOfWeekAndDate(LocalTime from, LocalTime to, DayOfWeek dayOfWeek, LocalDate date);
    Optional<List<Event>> getByDateAfter(LocalDate date);
    Optional<List<Event>> getByTagsInAndDateAfter(Set<Tag> tags, LocalDate date);
    Optional<List<Event>> getByTypeInAndDateAfter(Set<EventType> eventTypes, LocalDate date);
    Optional<List<Event>> getByCreatorIdAndDateEqualsOrDateAfter(Long creatorId, LocalDate equal, LocalDate after);
}
