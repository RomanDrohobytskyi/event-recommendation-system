package event.recommendation.system.notifications.service;

import event.recommendation.system.entities.User;
import event.recommendation.system.notifications.entity.Notification;
import event.recommendation.system.notifications.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository repository;

    //todo throw exception
    public Optional<Notification> getById(Long id) {
        return repository.findById(id);
    }

    public void save(Notification notification) {
        repository.save(notification);
    }

    public Set<Notification> findNotificationsByReceiversIsContaining(User user) {
        return repository.findNotificationsByReceiversIsContaining(user);
    }

}
