package event.recommendation.system.date;

import java.time.LocalTime;
import java.util.Optional;

import static event.recommendation.system.logger.LoggerJ.logError;

public class TimeParser {

    public static Optional<LocalTime> parseToLocalTime(String hourAndMinutes) {
            String[] hoursAndMinutesArray = hourAndMinutes.split("[-:;,\\s+]");
            if (hoursAndMinutesArray.length != 2) {
                throw new IllegalArgumentException("Cannot parse time: " + hourAndMinutes);
            } else {
                String hour = hoursAndMinutesArray[0];
                String minutes = hoursAndMinutesArray[1];
                try {
                    int hourInt = Integer.parseInt(hour);
                    int minutesInt = Integer.parseInt(minutes);
                    LocalTime time = LocalTime.of(hourInt, minutesInt);
                    return Optional.of(time);
                } catch (NumberFormatException  e) {
                    logError(TimeParser.class, "NumberFormatException during " +
                            "parsing hours and minutes: "+ hour +"h " + minutes + "m.");
                }
            }
        return Optional.empty();
    }
}
