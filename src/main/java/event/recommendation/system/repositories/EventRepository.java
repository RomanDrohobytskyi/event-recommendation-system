package event.recommendation.system.repositories;

import event.recommendation.system.entities.event.Event;
import event.recommendation.system.models.DayOfWeek;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    Optional<List<Event>> getEventsByDateBetweenOrderByDate(Date from, Date to);
    Optional<Event> getFirstByFromBeforeAndToBeforeAndDayOfWeekAndDate(LocalTime from, LocalTime to, DayOfWeek dayOfWeek, Date date);
}
