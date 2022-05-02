package event.recommendation.system.subscription.subscriber.observable;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.User;
import event.recommendation.system.enums.SubscriptionType;

import java.util.Set;

//Observable
public interface Subscription {
    void subscribe(User user, Set<SubscriptionType> subscriptionTypes);
    void unsubscribe(User user, Set<SubscriptionType> subscriptionTypes);
    void notifySubscribers(Event event, SubscriptionType subscriptionType);
}
