package event.recommendation.system.subscription.subscriber.observer;

import event.recommendation.system.enums.SubscriptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriberService {
    private final EventCreationSubscriber creationSubscriber;
    private final EventModificationSubscriber eventModificationSubscriber;
    private final EventRegistrationSubscriber eventRegistrationSubscriber;
    private final EventRatingSubscriber eventRatingSubscriber;
    private final NoneSubscriber noneSubscriber;

    public Subscriber getByType(SubscriptionType type) {
        return switch (type) {
            case CREATION -> creationSubscriber;
            case MODIFICATION -> eventModificationSubscriber;
            case REGISTRATION -> eventRegistrationSubscriber;
            case RATING -> eventRatingSubscriber;
            case NONE -> noneSubscriber;
        };
    }
}
