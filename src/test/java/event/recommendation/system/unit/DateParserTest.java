package event.recommendation.system.unit;

import event.recommendation.system.date.DateParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DateParserTest {

    @Test
    void shouldParseDate(){
        String expectedDate = "Thu Feb 20 00:00:00 CET 2020";
        String date = "2020-02-20";

        Optional<Date> parsedDate = DateParser.parseStringToDateForDefaultPattern(date);

        assertTrue(parsedDate.isPresent());
        assertThat(parsedDate.get().toString()).isEqualTo(expectedDate);
    }

    @Test
    void shouldBeEmptyForWrongDate(){
        String unparseableDate = "231";

        Optional<Date> shouldBeEmptyParsedDate =  DateParser.parseStringToDateForDefaultPattern(unparseableDate);
        Optional<Date> expectedEmptyOptionalDate = Optional.empty();

        assertThat(shouldBeEmptyParsedDate).isEqualTo(expectedEmptyOptionalDate);
    }

    @Test
    void shouldThrowParseExceptionDate(){
        String unparseableDate = "231";

        assertThrows(ParseException.class, () -> DateParser.parseStringToDateForDefaultPattern(unparseableDate));
    }
}
