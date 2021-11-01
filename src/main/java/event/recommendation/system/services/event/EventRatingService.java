package event.recommendation.system.services.event;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.EventRating;
import event.recommendation.system.entities.User;
import event.recommendation.system.repositories.EventRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class EventRatingService {
    public static final int MIN_RATING_SCORE = 0;
    public static final int MAX_RATING_SCORE = 5;
    private final EventRatingRepository eventRatingRepository;

    public void rate(EventRating rating) {
        if (isRatingScoreValid(rating)) {
            rating.setDate(now());
            updateOrSave(rating);
        }
    }

    private boolean isRatingScoreValid(EventRating rating) {
        try {
            return (rating.getScore() > MIN_RATING_SCORE && rating.getScore() <= MAX_RATING_SCORE);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO: use only save
    private void updateOrSave(EventRating rating){
        Optional<EventRating> existingRating = getByUserAndEvent(rating.getEvaluator(), rating.getEvaluatedEvent());
        existingRating.ifPresentOrElse(this::save, () -> save(rating));
    }

    private Optional<EventRating> getByUserAndEvent(User user, Event event) {
        return eventRatingRepository.getByEvaluatorAndEvaluatedEvent(user, event);
    }

    private void save(EventRating eventRating) {
        eventRatingRepository.save(eventRating);
    }

    public List<Event> getEventsByEvaluatorAndDateAfter(User evaluator, LocalDateTime from) {
        return getByEvaluatorAndDateAfter(evaluator, from).stream()
                .map(EventRating::getEvaluatedEvent)
                .collect(toList());
    }

    private List<EventRating> getByEvaluatorAndDateAfter(User evaluator, LocalDateTime from) {
        return eventRatingRepository.getByEvaluatorAndDateAfter(evaluator, from);
    }
}
