package event.recommendation.system.enums;

import java.util.Arrays;

public enum EventType {
    NONE(0),
    SPORT(1),
    EDUCATION(2),
    ART(3),
    SOCIAL(4),
    CORPORATE(5),
    FESTIVAL(6);

    public final int type;

    EventType(int type){
        this.type = type;
    }

    public static EventType[] getEventsTypes(){
        return Arrays.stream(values())
                .filter(eventType -> !eventType.equals(NONE))
                .toArray(EventType[]::new);
    }
}
