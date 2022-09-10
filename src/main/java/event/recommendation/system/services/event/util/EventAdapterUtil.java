package event.recommendation.system.services.event.util;

import event.recommendation.system.models.DayOfWeek;

import java.util.Arrays;

public class EventAdapterUtil {

    public static DayOfWeek getDayOfWeekByNumber(int dayNumber) {
        return Arrays.stream(DayOfWeek.values())
                .filter(dayOfWeek -> dayOfWeek.getNumber() == dayNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Wrong day number - " + dayNumber));
    }

}
