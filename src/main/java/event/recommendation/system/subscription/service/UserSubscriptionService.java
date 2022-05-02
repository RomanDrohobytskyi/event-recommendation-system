package event.recommendation.system.subscription.service;

import event.recommendation.system.entities.User;
import event.recommendation.system.repositories.UserRepository;
import event.recommendation.system.subscription.entity.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class UserSubscriptionService {
    private final UserRepository userRepository;

    public Set<User> findBySubscriptions(Set<Subscription> subscriptions) {
        Set<Long> ids = subscriptions.stream()
                .map(Subscription::getId)
                .collect(toSet());
        return userRepository.findBySubscriptions(ids);
    }

}
