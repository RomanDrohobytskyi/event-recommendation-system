package event.recommendation.system.services.builders;

import event.recommendation.system.entities.Event;
import event.recommendation.system.entities.User;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.managers.UserManager;
import event.recommendation.system.services.event.EventService;
import event.recommendation.system.services.event.EventsMainService;
import event.recommendation.system.services.event.UserEventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

import static event.recommendation.system.enums.EventType.NONE;
import static event.recommendation.system.enums.EventType.getEventsTypes;
import static event.recommendation.system.menu.MenuTabs.defaultMenu;
import static event.recommendation.system.menu.MenuTabs.loginMenu;
import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class ModelBuilder {
    private final UserEventsService userEventsService;
    private final EventService eventService;
    private final EventsMainService eventsMainService;

    public ModelBuilderService builder(Model model) {
        requireNonNull(model);
        return new ModelBuilderService(model);
    }

    public class ModelBuilderService {
        private final Model model;
        private final User loggedInUser = new UserManager().getLoggedInUser();

        public ModelBuilderService(Model model) {
            this.model = model;
        }

        public Model build() {
            return this.model;
        }

        public ModelBuilderService withUserCalendarEvents(EventType eventType) {
            Map<String, List<Event>> events = eventService.getWeekEvents(eventType);
            model.addAttribute("events", events);
            return this;
        }

        public ModelBuilderService withUserOutOfDateEvents() {
            model.addAttribute("outOfDateEvents", userEventsService.getOutOfDateEvents(loggedInUser));
            return this;
        }

        public ModelBuilderService withLoggedUser() {
            model.addAttribute("user", loggedInUser);
            return this;
        }

        public ModelBuilderService withPreferredAndOtherEvents() {
            List<Event> userPreferencesEvents = eventsMainService.getEventsForUser(loggedInUser);
            List<Event> allEvents = eventsMainService.getOtherEvents(userPreferencesEvents);

            model.addAttribute("userPreferencesEvents", userPreferencesEvents);
            model.addAttribute("otherEvents", allEvents);

            return this;
        }

        public ModelBuilderService withEventTypes() {
            model.addAttribute("eventTypes", getEventsTypes());
            return this;
        }

        public ModelBuilderService withAllAndAvailableToRegisterEvents() {
            Map<String, List<Event>> events = eventService.getWeekEvents(NONE);
            List<Event> availableEvents = eventService.filterEventsAvailableToRegistration(events, loggedInUser);

            model.addAttribute("events", events);
            model.addAttribute("availableEvents", availableEvents);

            return this;
        }

        public ModelBuilderService withCreatedEventsByUser() {
            List<Event> createdActiveEvents = eventsMainService.getCreatedActiveEvents(loggedInUser);
            model.addAttribute("createdActiveEvents", createdActiveEvents);
            return this;
        }

        public ModelBuilderService withDeletedEventsByUser() {
            List<Event> deletedNotActiveEvents = eventsMainService.getDeletedNotActiveEvents(loggedInUser);
            model.addAttribute("deletedNotActiveEvents", deletedNotActiveEvents);
            return this;
        }

        /* MENU */

        public ModelBuilderService withDefaultMenu() {
            defaultMenu(model);
            return this;
        }

        public ModelBuilderService withLoginMenu() {
            loginMenu(model);
            return this;
        }

        /* Custom parameters */

        public ModelBuilderService withStringParam(String name, String value) {
            model.addAttribute(name, value);
            return this;
        }
    }
}
