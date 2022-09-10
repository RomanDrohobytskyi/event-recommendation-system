package event.recommendation.system.services.recommendation.algorithm;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.EventRating;
import event.recommendation.system.entities.Tag;
import event.recommendation.system.entities.User;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.services.event.EventRatingService;
import event.recommendation.system.services.event.EventService;
import event.recommendation.system.services.tag.TagService;
import event.recommendation.system.services.user.preferences.UserPreferencesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static event.recommendation.system.date.LocalDateTimeUtils.nowMinusTwoMonths;
import static java.util.Collections.emptyList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

/**
 * RecommendationAlgorithmService - it is a recommendation algorithm that based on a user preferences would recommend
 * events for a user which user will probably like and may be interested in
 */
@Service
@RequiredArgsConstructor
public class EventRecommendationAlgorithmService {
    private final static int HIGHEST_AVERAGE_RATE = 4;
    private final EventService eventService;
    private final UserPreferencesService userPreferencesService;
    private final TagService tagService;
    private final EventRatingService eventRatingService;
    private User user;

    public List<Event> getRecommendedEventsForUser(User user) {
        this.user = user;
        return getRecommendedEventsForUserOrderedByRates();
    }

    private List<Event> getRecommendedEventsForUserOrderedByRates() {
        List<Event> recommendedEvents = new ArrayList<>();
        recommendedEvents.addAll(getEventsBasedOnSelectedUserPreferences());
        recommendedEvents.addAll(getEventsBasedOnUserRates());
        return getUniqEventWithHighestScoreSortedByScore(recommendedEvents);
    }

    private List<Event> getUniqEventWithHighestScoreSortedByScore(List<Event> events) {
        return events.stream()
                .distinct()
                .filter(event -> event.getAverageScore() >= 4)
                .sorted(comparing(Event::getAverageScore).reversed())
                .collect(toList());
    }

    private List<Event> getEventsBasedOnUserRates() {
        List<Event> ratedByUser = getRatedEvents();
        return getSimilarEvents(ratedByUser);
    }

    private List<Event> getRatedEvents() {
        if(hasRates()) {
            List<Event> twoMonthsRatedEvents = getRatedEventsForLastTwoMoths();
            return isNotEmpty(twoMonthsRatedEvents) ? twoMonthsRatedEvents : getRatedUserEvents();
        }
        return emptyList();
    }

    private List<Event> getRatedEventsForLastTwoMoths() {
        return eventRatingService.getEventsByEvaluatorAndDateAfter(user, nowMinusTwoMonths());
    }

    private List<Event> getRatedUserEvents() {
        return hasRates()
                ? user.getRates().stream()
                .filter(eventRating -> eventRating.getScore() >= HIGHEST_AVERAGE_RATE)
                    .map(EventRating::getEvaluatedEvent)
                    .collect(toList())
                : emptyList();
    }

    private boolean hasRates() {
        return isNotEmpty(user.getRates());
    }

    private List<Event> getEventsBasedOnSelectedUserPreferences() {
        if(userPreferencesService.hasSelectedPreferences(user)) {
            Set<EventType> userSelectedEventTypes = userPreferencesService.getUserPreferredEventTypes(user);
            return eventService.getActiveAndActualEventsByTypesSortedByRate(userSelectedEventTypes)
                    .orElseGet(Collections::emptyList);
        }
        return emptyList();
    }

    private List<Event> getSimilarEvents(List<Event> events) {
        List<EventType> typesOfRatedEvents = events.stream()
                .map(Event::getType)
                .distinct()
                .collect(toList());
        return getEventsByEventTypes(typesOfRatedEvents);
    }

    private List<Event> getEventsByEventTypes(List<EventType> types) {
        Set<Tag> tags = tagService.getTagsByEventTypes(types);
        return getEventsByTag(tags);
    }

    private List<Event> getEventsByTag(Set<Tag> tags) {
        return eventService.getByTags(tags)
                .orElseGet(Collections::emptyList);
    }
}
