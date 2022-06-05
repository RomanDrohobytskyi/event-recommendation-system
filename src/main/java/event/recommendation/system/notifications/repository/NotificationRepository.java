package event.recommendation.system.notifications.repository;

import event.recommendation.system.entities.User;
import event.recommendation.system.notifications.entity.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {
    Set<Notification> findNotificationsByReceiversIsContaining(User user);
}
