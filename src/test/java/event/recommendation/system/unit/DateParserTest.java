package event.recommendation.system.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;

import static event.recommendation.system.date.DateParser.getCurrentDateWithoutTime;
import static event.recommendation.system.date.DateParser.parseStringToDateForDefaultPattern;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DateParserTest {

    @Test
    void shouldParseDate() {
        String expectedDate = "Thu Feb 20 00:00:00 CET 2020";
        String date = "2020-02-20";

        Optional<Date> parsedDate = parseStringToDateForDefaultPattern(date);

        assertTrue(parsedDate.isPresent());
        assertThat(parsedDate.get().toString()).isEqualTo(expectedDate);
    }

    @Test
    void shouldBeEmptyForWrongDate() {
        String unparseableDate = "231";

        Optional<Date> shouldBeEmptyParsedDate =  parseStringToDateForDefaultPattern(unparseableDate);
        Optional<Date> expectedEmptyOptionalDate = Optional.empty();

        assertThat(shouldBeEmptyParsedDate).isEqualTo(expectedEmptyOptionalDate);
    }

    @Test
    public void shouldFormatCurrentDateWithoutTime() {
        Date currentDate = new Date();

        Date formattedCurrentDateWithoutTime = getCurrentDateWithoutTime();

        assertThat(currentDate.toString()).isEqualTo(formattedCurrentDateWithoutTime);
    }
    
}
