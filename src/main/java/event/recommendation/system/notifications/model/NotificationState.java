package event.recommendation.system.notifications.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum NotificationState {
    NEW(0),
    READ(1);

    public final int state;

}
