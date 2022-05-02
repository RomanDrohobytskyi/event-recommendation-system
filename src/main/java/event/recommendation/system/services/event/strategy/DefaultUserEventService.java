package event.recommendation.system.services.event.strategy;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.User;
import event.recommendation.system.managers.UserManager;
import event.recommendation.system.models.DayOfWeek;
import event.recommendation.system.services.event.UserEventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static java.time.LocalDate.now;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class DefaultUserEventService implements EventsService {
    private final UserEventsService userEventsService;

    /*TODO refactoring*/


    @Override
    public Map<String, List<Event>> filterEvents() {
        return getSortedEventsForFullWeekFromTodayWithDay();
    }

    public Map<String, List<Event>> getSortedEventsForFullWeekFromTodayWithDay() {
        List<String> weekDaysNames = getWeekDaysNames();
        Map<String, List<Event>> events = fillEmptyDaysOfWeek(weekDaysNames);

        return weekDaysNames.stream()
                .collect(LinkedHashMap::new,
                        (map, day) -> map.put(day, events.get(day)),
                        Map::putAll);
    }

    public List<String> getWeekDaysNames() {
        int dayOfWeek = now().getDayOfWeek().getValue();

        DayOfWeek[] weekDaysFromToday = Arrays.copyOfRange(DayOfWeek.values(), dayOfWeek, 7);
        DayOfWeek[] weekDaysBeforeToday = Arrays.copyOfRange(DayOfWeek.values(), 0, dayOfWeek);

        DayOfWeek[] weekDays = Stream.of(weekDaysFromToday, weekDaysBeforeToday)
                .flatMap(Stream::of)
                .toArray(DayOfWeek[]::new);

        return Stream.of(weekDays)
                .map(DayOfWeek::getDay)
                .collect(toList());
    }

    private Map<String, List<Event>> fillEmptyDaysOfWeek(List<String> weekDaysSortedFromToday) {
        Map<String, List<Event>> events = getEventsForWeekFromTodayWithDay();
        weekDaysSortedFromToday.stream()
                .filter(day -> !events.containsKey(day))
                .forEach(day -> events.put(day, Collections.emptyList()));
        return events;
    }

    private Map<String, List<Event>> getEventsForWeekFromTodayWithDay() {
        return getEventsForWeekFromToday().stream()
                .collect(groupingBy(a -> a.getDayOfWeek().getDay()));
    }

    private List<Event> getEventsForWeekFromToday() {
        return getEventsByDateBetweenOrderByDate(now(), now().plusDays(6))
                .orElseGet(Collections::emptyList);
    }


    private Optional<List<Event>> getEventsByDateBetweenOrderByDate(LocalDate from, LocalDate to) {
        UserManager userManager = new UserManager();
        User loggedInUser = userManager.getLoggedInUser();

        return userEventsService.getEventsByParticipantAndDateAfter(loggedInUser, from);



        //return eventRepository.getEventsForDateRage(from, to);
    }
}
