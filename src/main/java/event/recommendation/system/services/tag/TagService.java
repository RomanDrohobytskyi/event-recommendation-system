package event.recommendation.system.services.tag;

import event.recommendation.system.entities.Tag;
import event.recommendation.system.enums.EventType;
import event.recommendation.system.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Set<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Map<String, List<Tag>> getAllTagsGroupedByTypeString() {
        return getAllTags().stream()
                .collect(groupingBy(tag -> tag.getType().toString()));
    }

    public Set<Tag> getTagsByEventTypes(Collection<EventType> eventTypes) {
        return tagRepository.findAllByTypeIn(eventTypes);
    }

    public Set<Tag> getTagsFromForm(Map<String, String> form) {
        return new HashSet<>();
    }
}
