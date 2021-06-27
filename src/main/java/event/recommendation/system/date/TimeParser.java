package event.recommendation.system.date;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.util.Optional;

@Slf4j
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
                    log.error("NumberFormatException during " +
                            "parsing hours and minutes: "+ hour +"h " + minutes + "m.");
                    return Optional.empty();
                }
            }
    }
}
