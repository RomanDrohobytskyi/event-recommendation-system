package event.recommendation.system.services.event;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.EventRating;
import event.recommendation.system.entities.User;
import event.recommendation.system.repositories.EventRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventRatingService {
    public static final int MIN_RATING_SCORE = 0;
    public static final int MAX_RATING_SCORE = 5;
    private final EventRatingRepository eventRatingRepository;

    public void rate(EventRating rating) {
        if (isRatingScoreValid(rating)) {
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
}
