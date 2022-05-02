package event.recommendation.system.subscription.subscriber.observer;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.User;
import event.recommendation.system.notifications.entity.Notification;
import event.recommendation.system.notifications.service.NotificationService;
import event.recommendation.system.notifications.service.UserNotificationService;
import event.recommendation.system.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

import static event.recommendation.system.enums.SubscriptionType.MODIFICATION;

@Component
@RequiredArgsConstructor
public class EventModificationSubscriber implements Subscriber {
    private final UserNotificationService userNotificationService;
    private final UserService userService;
    private final NotificationService notificationService;

    @Override
    public void update(Event event) {
        Set<User> subscribers = userNotificationService.getSubscribers(MODIFICATION);
        Notification notification = createNotification(event, subscribers);
        notificationService.save(notification);
        addNotificationToUsers(notification, subscribers);
    }

    private Notification createNotification(Event event, Set<User> receivers) {
        String title = event.getTitle() + " was modified by creator";
        String message = event.getTitle() + " - was modified by creator - " + event.getCreator().getNames()
                + ". Check updates before the visiting!";
        return Notification.builder()
                .creationDate(LocalDateTime.now())
                .content(message)
                .receivers(receivers)
                .title(title)
                .build();
    }

    private void addNotificationToUsers(Notification notification, Set<User> users) {
        users.stream()
                .peek(user -> user.getNotifications().add(notification))
                .forEach(userService::save);
    }
}
