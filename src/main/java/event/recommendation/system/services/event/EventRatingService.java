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
    private final EventRatingRepository eventRatingRepository;

    public void rate(EventRating rating) {
        if (isScoreValid(rating)) {
            updateOrSave(rating);
        }
    }

    private boolean isScoreValid(EventRating rating) {
        try {
            return (rating.getScore() > 0 && rating.getScore() <=5);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void updateOrSave(EventRating rating){
        Optional<EventRating> existingRating = getByUserAndEvent(rating.getEvaluator(), rating.getEvaluatedEvent());
        if(existingRating.isPresent()) {
            save(existingRating.get());
        } else {
            save(rating);
        }
    }

    private Optional<EventRating> getByUserAndEvent(User user, Event event) {
        return eventRatingRepository.getByEvaluatorAndEvaluatedEvent(user, event);
    }

    private void save(EventRating eventRating) {
        eventRatingRepository.save(eventRating);
    }
}
