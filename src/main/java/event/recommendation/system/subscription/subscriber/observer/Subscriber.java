package event.recommendation.system.subscription.subscriber.observer;

import event.recommendation.system.entities.Event;

//Observer
public interface Subscriber {
    //void addUserSubscriber(User user);
    void update(Event event);
}
