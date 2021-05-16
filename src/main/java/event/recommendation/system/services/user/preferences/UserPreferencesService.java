package event.recommendation.system.services.user.preferences;

import event.recommendation.system.entities.Tag;
import event.recommendation.system.entities.User;
import event.recommendation.system.services.tag.TagService;
import event.recommendation.system.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserPreferencesService {
    private final TagService tagService;
    private final UserService userService;

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

}
