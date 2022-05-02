package event.recommendation.system.subscription.subscriber.observable;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.User;
import event.recommendation.system.enums.SubscriptionType;
import event.recommendation.system.notifications.service.UserNotificationService;
import event.recommendation.system.services.user.UserService;
import event.recommendation.system.subscription.entity.Subscription;
import event.recommendation.system.subscription.service.SubscriptionService;
import event.recommendation.system.subscription.subscriber.observer.Subscriber;
import event.recommendation.system.subscription.subscriber.observer.SubscriberService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static event.recommendation.system.enums.SubscriptionType.values;

@Service
public class NotificationSubscription implements event.recommendation.system.subscription.subscriber.observable.Subscription {
    private final Map<Subscription, Set<User>> subscriptions = new HashMap<>();

    private final SubscriptionService subscriptionService;
    private final UserNotificationService userNotificationService;
    private final SubscriberService subscriberService;
    private final UserService userService;

    public NotificationSubscription(SubscriptionService subscriptionService, UserNotificationService userNotificationService, SubscriberService subscriberService, UserService userService) {
        this.subscriptionService = subscriptionService;
        this.userNotificationService = userNotificationService;
        this.subscriberService = subscriberService;
        this.userService = userService;

        initSubscribers();
    }

    private void initSubscribers() {
        for (SubscriptionType type : values()) {
            Subscription subscription = subscriptionService.getByType(type);
            Set<User> subscribers = userNotificationService.getSubscribers(subscription);
            subscriptions.put(subscription, subscribers);
        }
    }

    @Override
    public void subscribe(User user, Set<SubscriptionType> subscriptionTypes) {
        for(SubscriptionType type : subscriptionTypes) {
            Subscription subscription = subscriptionService.getByType(type);
            for (Map.Entry<Subscription, Set<User>> entry : subscriptions.entrySet()) {
                if (entry.getKey().getId().equals(subscription.getId())) {
                    user.getSubscriptions().add(subscription);
                }
            }
        }
        userService.save(user);
    }

    @Override
    public void unsubscribe(User user, Set<SubscriptionType> subscriptionTypes) {
        for(SubscriptionType type : subscriptionTypes) {
            Subscription subscription = subscriptionService.getByType(type);
            subscriptions.get(subscription).removeIf(subscriber -> subscriber.getId().equals(user.getId()));
        }
    }

    @Override
    public void notifySubscribers(Event event, SubscriptionType subscriptionType) {
        Subscriber subscriber = subscriberService.getByType(subscriptionType);
        subscriber.update(event);
    }
}