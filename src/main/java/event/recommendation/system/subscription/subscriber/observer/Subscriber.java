package event.recommendation.system.subscription.subscriber.observer;

import event.recommendation.system.entities.Event;

//Observer
public interface Subscriber {
    void update(Event event);
}
