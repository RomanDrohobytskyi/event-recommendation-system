package event.recommendation.system.services.controllers;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.EventRating;
import event.recommendation.system.entities.User;
import event.recommendation.system.services.builders.ModelBuilder;
import event.recommendation.system.services.event.EventRatingService;
import event.recommendation.system.services.event.EventService;
import event.recommendation.system.subscription.subscriber.observable.NotificationSubscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import static event.recommendation.system.enums.SubscriptionType.RATING;
import static event.recommendation.system.enums.SubscriptionType.REGISTRATION;

@Service
@RequiredArgsConstructor
public class EventsMainControllerService {
    private final ModelBuilder modelBuilder;
    private final EventService eventService;
    private final NotificationSubscription notificationSubscription;
    private final EventRatingService eventRatingService;

    public void onEvents(Model model) {
        modelBuilder.builder(model)
                .withDefaultMenu()
                .withEventTypes()
                .withPreferredAndOtherEvents()
                .withAllAndAvailableToRegisterEvents()
                .withLoggedUser();
    }

    public void onRegisterUserOnEvent(User user, Event event, Model model) {
        eventService.registerUserForEvent(user, event);
        notificationSubscription.notifySubscribers(event, REGISTRATION);
    }

    public void onRate(EventRating eventRating) {
        eventRatingService.rate(eventRating);
        notificationSubscription.notifySubscribers(eventRating.getEvaluatedEvent(), RATING);
    }
}
