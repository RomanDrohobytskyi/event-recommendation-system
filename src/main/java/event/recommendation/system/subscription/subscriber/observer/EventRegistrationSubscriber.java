package event.recommendation.system.subscription.subscriber.observer;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.User;
import event.recommendation.system.notifications.entity.Notification;
import event.recommendation.system.notifications.service.NotificationService;
import event.recommendation.system.notifications.service.UserNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

import static event.recommendation.system.enums.SubscriptionType.REGISTRATION;

/*TODO: should be used only for creators!*/
@Component
@RequiredArgsConstructor
public class EventRegistrationSubscriber implements Subscriber {

    private final UserNotificationService userNotificationService;
    private final NotificationService notificationService;

    @Override
    public void update(Event event) {
        Set<User> subscribers = userNotificationService.getSubscribers(REGISTRATION);
        Notification notification = createNotification(event, subscribers);
        notificationService.save(notification);
    }

    private Notification createNotification(Event event, Set<User> receivers) {
        String title = "New user registered to visit event - " + event.getTitle();
        String message = "New visitor registered to visit event - " + event.getTitle() + ", created by - " + event.getCreator().getNames();
        return Notification.builder()
                .creationDate(LocalDateTime.now())
                .content(message)
                .receivers(receivers)
                .title(title)
                .build();
    }
}
