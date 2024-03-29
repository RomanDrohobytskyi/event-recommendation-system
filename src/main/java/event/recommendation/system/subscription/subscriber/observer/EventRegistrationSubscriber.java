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

/*TODO: should be used only for creators!*/
@Component
@RequiredArgsConstructor
public class EventRegistrationSubscriber implements Subscriber {
    private final static String NOTIFICATION_TITLE = "New user registered to visit event - %s!";
    private final static String NOTIFICATION_MESSAGE = "New visitor registered to visit event - %s, created by - %s.";

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
        String message = format(NOTIFICATION_MESSAGE, event.getTitle(), event.getCreator().getNames());
        return Notification.builder()
                .creationDate(now())
                .content(message)
                .receivers(receivers)
                .title(title)
                .state(NEW)
                .build();
    }
}
