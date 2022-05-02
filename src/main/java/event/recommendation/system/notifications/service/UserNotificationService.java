package event.recommendation.system.notifications.service;

import event.recommendation.system.entities.User;
import event.recommendation.system.enums.SubscriptionType;
import event.recommendation.system.subscription.entity.Subscription;
import event.recommendation.system.subscription.service.SubscriptionService;
import event.recommendation.system.subscription.service.UserSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.Set.of;

@Service
@RequiredArgsConstructor
public class UserNotificationService {
    private final SubscriptionService subscriptionService;
    private final UserSubscriptionService userSubscriptionService;

    public Set<User> getSubscribers(SubscriptionType type) {
        Subscription subscription = subscriptionService.getByType(type);
        return getSubscribers(subscription);
    }

    public Set<User> getSubscribers(Subscription subscription) {
        return userSubscriptionService.findBySubscriptions(of(subscription));
    }

}
