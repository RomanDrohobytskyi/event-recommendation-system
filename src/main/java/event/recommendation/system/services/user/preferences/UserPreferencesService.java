package event.recommendation.system.services.user.preferences;

import event.recommendation.system.entities.Tag;
import event.recommendation.system.entities.User;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.managers.UserManager;
import event.recommendation.system.roles.Role;
import event.recommendation.system.services.tag.TagService;
import event.recommendation.system.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

@Service
@RequiredArgsConstructor
public class UserPreferencesService {
    private final TagService tagService;
    private final UserService userService;
    private final UserManager userManager = new UserManager();

    public void addUserAndMenu(Model model) {
        model.addAttribute("user", userManager.getLoggedInUser());
        model.addAttribute("roles", Role.values());
        model.addAttribute("tags", getTags());
    }

    public Map<String, List<Tag>> getTags() {
        return tagService.getAllTagsGroupedByTypeString();
    }

    public void saveUserPreferences(User user, Map<String, String> form) {
        Set<Tag> tags = tagService.getAllTags();
        user.setTags(new HashSet<>());
        tags.stream()
                .filter(tag -> form.containsKey(tag.getName()))
                .forEach(tag -> user.getTags().add(tag));
        userService.save(user);
    }

    public Set<EventType> getUserPreferredEventTypes(User user) {
        return user.getTags().stream()
                .map(Tag::getType)
                .collect(toSet());
    }

    public boolean hasSelectedPreferences(User user) {
        return isNotEmpty(user.getTags()) && user.getTags().size() > 0;
    }
}
