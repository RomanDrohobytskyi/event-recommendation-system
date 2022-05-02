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

import static event.recommendation.system.enums.SubscriptionType.CREATION;
import static event.recommendation.system.notifications.model.NotificationState.NEW;

@Component
@RequiredArgsConstructor
public class EventCreationSubscriber implements Subscriber {
    private final NotificationService notificationService;
    private final UserNotificationService userNotificationService;
    private final UserService userService;

    @Override
    public void update(Event event) {
        Set<User> subscribers = userNotificationService.getSubscribers(CREATION);
        Notification notification = createNotification(event, subscribers);
        notificationService.save(notification);
        addNotificationToUsers(notification, subscribers);
    }

    private Notification createNotification(Event event, Set<User> receivers) {
        String title = "New event was created - " + event.getTitle();
        String message = event.getTitle() + " - new event was created by - " + event.getCreator().getNames();
        return Notification.builder()
                .creationDate(LocalDateTime.now())
                .content(message)
                .receivers(receivers)
                .state(NEW)
                .title(title)
                .build();
    }

    private void addNotificationToUsers(Notification notification, Set<User> users) {
        users.stream()
                .peek(user -> user.getNotifications().add(notification))
                .forEach(userService::save);
    }
}
