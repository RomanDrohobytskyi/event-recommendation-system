package event.recommendation.system.services.event;

import event.recommendation.system.entities.EventSpace;
import event.recommendation.system.repositories.EventSpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventSpaceService {
    private final EventSpaceRepository eventSpaceRepository;

    public void save(EventSpace eventSpace) {
        eventSpaceRepository.save(eventSpace);
    }
}
