package event.recommendation.system.services.tag;

import event.recommendation.system.entities.tag.Tag;
import event.recommendation.system.entities.user.User;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Set<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Map<String, List<Tag>> getAllUserTagsGroupedByType(User user) {
        return user.getTags().stream()
                .collect(groupingBy(tag -> tag.getType().toString()));
    }

    public Map<EventType, List<Tag>> getAllTagsGroupedByType() {
        return getAllTags().stream()
                .collect(groupingBy(Tag::getType));
    }

    public Map<String, List<Tag>> getAllTagsGroupedByTypeString() {
        return getAllTags().stream()
                .collect(groupingBy(tag -> tag.getType().toString()));
    }

}
