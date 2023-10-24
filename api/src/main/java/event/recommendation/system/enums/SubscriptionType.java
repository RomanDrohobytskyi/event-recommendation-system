package event.recommendation.system.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

@Getter
@RequiredArgsConstructor
public enum SubscriptionType {
    NONE (1L, "NONE", "None"),
    CREATION (2L, "CREATION", "Event creation subscription"),
    MODIFICATION (3L, "MODIFICATION", "Event modification subscription"),
    REGISTRATION (4L, "REGISTRATION","Event registration subscription"),
    RATING (5L, "RATING","Event rating subscription");

    private final Long id;
    private final String type;
    private final String description;

    public static List<String> getAvailableSubscriptionsDescriptions() {
        List<String> availableSubscriptions = getSubscriptionsTypes();
        availableSubscriptions.removeIf(type -> type.equals(NONE.type));
        return availableSubscriptions;
    }

    public static List<String> getSubscriptionsTypes() {
        return asStream()
                .map(SubscriptionType::getType)
                .collect(Collectors.toList());
    }

    public static Stream<SubscriptionType> asStream() {
        return stream(values());
    }
}
