package event.recommendation.system.mock.event.service;

import event.recommendation.system.entities.Event;
import event.recommendation.system.models.DayOfWeek;
import event.recommendation.system.services.event.UserEventsService;
import event.recommendation.system.services.event.strategy.DefaultUserEventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static event.recommendation.system.enums.EventType.*;
import static java.time.LocalDate.now;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EventServiceTest {
    @Mock
    private UserEventsService userEventsService;
    @InjectMocks
    private DefaultUserEventService defaultUserEventService;


    public void init() {
        DayOfWeek currentDayOfWeek = null;
        Event educationalEvent = Event.builder().type(EDUCATION).date(now()).dayOfWeek(currentDayOfWeek).build();
        Event sportEvent = Event.builder().type(SPORT).build();
        Event artEvent = Event.builder().type(ART).build();
        when(userEventsService.getEventsByParticipantAndDateAfter(any(), any()))
                .thenReturn(Optional.of(List.of(educationalEvent, sportEvent, artEvent)));
    }

    @Test
    public void shouldFilterAllEvents() {
        init();
        Map<String, List<Event>> filteredEvents = defaultUserEventService.filterEvents();
    }
}
