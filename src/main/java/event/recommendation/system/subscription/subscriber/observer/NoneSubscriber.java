package event.recommendation.system.subscription.subscriber.observer;

import event.recommendation.system.entities.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoneSubscriber implements Subscriber {

    @Override
    public void update(Event event) {}
}
