package event.recommendation.system.subscription.service;

import event.recommendation.system.enums.SubscriptionType;
import event.recommendation.system.services.user.UserService;
import event.recommendation.system.subscription.entity.Subscription;
import event.recommendation.system.subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final UserService userService;
    private final SubscriptionRepository subscriptionRepository;

    public Set<Subscription> findAll() {
        return (Set<Subscription>) subscriptionRepository.findAll();
    }

    //TODO
    public Subscription getByType(SubscriptionType type) {
        return subscriptionRepository.findById(type.getId()).orElseThrow(() -> new IllegalArgumentException("Was not found!"));
    }

    public Subscription findById(Long id) {
        return subscriptionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No subscription type with id - " + id));

    }
}
