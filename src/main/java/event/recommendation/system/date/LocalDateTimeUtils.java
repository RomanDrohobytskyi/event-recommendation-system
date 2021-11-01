package event.recommendation.system.date;

import java.time.LocalDateTime;

public class LocalDateTimeUtils {

    public static LocalDateTime nowMinusTwoMonths() {
        return LocalDateTime.now().minusMonths(2);
    }
}
