package event.recommendation.system.services.recommendation.algorithm;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.EventRating;
import event.recommendation.system.entities.Tag;
import event.recommendation.system.entities.User;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.services.event.EventService;
import event.recommendation.system.services.tag.TagService;
import event.recommendation.system.services.user.preferences.UserPreferencesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.List.of;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

/**
 * RecommendationAlgorithmService - it is a recommendation algorithm that based on a user preferences would recommend
 * events for a user which user will probably like and may be interested in
 */
@Service
@RequiredArgsConstructor
public class EventRecommendationAlgorithmService {
    private final EventService eventService;
    private final UserPreferencesService userPreferencesService;
    private final TagService tagService;
    private User user;
    private List<Event> ratedEvents;
    private List<Event> userPreferredEvents;
    private List<Event> mostRatedEvents;

    //TODO
    public List<Event> getRecommendedEventsForUser(User user) {
        init(user);

       return getRecommendedEventsForUserOrderedByRates();
    }

    private void init(User user) {
        this.user = user;
        this.ratedEvents = getRatedUserEvents();
        this.userPreferredEvents = getEventsBasedOnSelectedUserPreferences();
    }

    private List<Event> getRecommendedEventsForUserOrderedByRates() {
        List<Event> recommendedEvents = new ArrayList<>();
        recommendedEvents.addAll(userPreferredEvents);
        //recommendedEvents.addAll(ratedEvents);

        return sortByRateDesc(recommendedEvents);
    }

    private List<Event> sortByRateDesc(List<Event> events) {
        return events.stream()
                .distinct()
                .sorted(Comparator.comparing(Event::getAverageScore)
                        .reversed())
                .collect(Collectors.toList());
    }


    private List<Event> getRatedUserEvents() {
        return hasRates()
                ? user.getRates().stream()
                    .map(EventRating::getEvaluatedEvent)
                    .collect(toList())
                : emptyList();
    }

    private boolean hasRates() {
        return isNotEmpty(user.getRates()) && user.getRates().size() > 0;
    }

    private List<Event> getEventsBasedOnSelectedUserPreferences() {
        if(userPreferencesService.hasSelectedPreferences(user)) {
            Set<EventType> userSelectedEventTypes = userPreferencesService.getUserPreferredEventTypes(user);
            return eventService.getActiveAndActualEventsByTypesSortedByRate(userSelectedEventTypes)
                    .orElseGet(Collections::emptyList);
        }
        return emptyList();
    }

    private Set<Tag> getTagsByEventTypes(List<EventType> eventTypes) {
        return tagService.getTagsByEventTypes(eventTypes);
    }

    private List<Event> getEventsByTagCategory(EventType type) {
        Set<Tag> tags = tagService.getTagsByEventTypes(of(type));
        return getEventsByTag(tags);
    }

    private List<Event> getEventsByTag(Set<Tag> tags) {
        return eventService.getByTags(tags)
                .orElseGet(Collections::emptyList);
    }
}
