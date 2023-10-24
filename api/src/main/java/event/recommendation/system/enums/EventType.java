package event.recommendation.system.enums;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum EventType {
    NONE(0),
    SPORT(1),
    EDUCATION(2),
    ART(3),
    SOCIAL(4),
    CORPORATE(5),
    FESTIVAL(6);

    public final int type;

    public static EventType[] getEventsTypes() {
        return Arrays.stream(values())
                .filter(eventType -> !eventType.equals(NONE))
                .toArray(EventType[]::new);
    }
}
