package event.recommendation.system.subscription.subscriber.observer;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.User;
import event.recommendation.system.notifications.entity.Notification;
import event.recommendation.system.notifications.service.NotificationService;
import event.recommendation.system.notifications.service.UserNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

import static event.recommendation.system.enums.SubscriptionType.REGISTRATION;
import static event.recommendation.system.notifications.model.NotificationState.NEW;
import static java.lang.String.format;
import static java.time.LocalDateTime.now;

@Component
@RequiredArgsConstructor
public class EventRatingSubscriber implements Subscriber {
    private final static String NOTIFICATION_TITLE = "Event - %s has been rated!";
    private final static String NOTIFICATION_MESSAGE = "We registered a new rate for event - %s given by the user.";

    private final UserNotificationService userNotificationService;
    private final NotificationService notificationService;

    @Override
    public void update(Event event) {
        Set<User> subscribers = userNotificationService.getSubscribers(REGISTRATION);
        Notification notification = createNotification(event, subscribers);
        notificationService.save(notification);
    }

    private Notification createNotification(Event event, Set<User> receivers) {
        String title = format(NOTIFICATION_TITLE, event.getTitle());
        String message = format(NOTIFICATION_MESSAGE, event.getTitle());
        return Notification.builder()
                .creationDate(now())
                .content(message)
                .receivers(receivers)
                .title(title)
                .state(NEW)
                .build();
    }
}
