package event.recommendation.system.date;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Slf4j
public class DateParser {
    private final static String YYYY_MM_DD = "yyyy-MM-dd";

    public static Date getCurrentDateWithoutTime() {
        return getDateWithoutTime(new Date());
    }

    public static Date getDateWithoutTime(String stringDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD);
        return DateParser.parseStringToDateForDefaultPattern(stringDate)
                .map(DateParser::getDateWithoutTime)
                .orElseThrow(() -> new IllegalArgumentException("Cannot format date: " + stringDate));
    }

    public static Date getDateWithoutTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD);
        try {
            return formatter.parse(formatter.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Optional<Date> parseStringToDateForDefaultPattern(String date) {
        try {
            Date parsedDate = new SimpleDateFormat(YYYY_MM_DD).parse(date);
            return Optional.of(parsedDate);
        } catch (ParseException e) {
            log.error("Could not parse date: " + date);
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
