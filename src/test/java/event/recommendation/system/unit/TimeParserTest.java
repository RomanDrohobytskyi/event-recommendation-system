package event.recommendation.system.unit;

import event.recommendation.system.date.TimeParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TimeParserTest {

    @Test
    public void shouldParseTimeWithColon() {
        String timeWithColon = "12:30";

        Optional<LocalTime> time = TimeParser.parseToLocalTime(timeWithColon);

        assertThat(time).isNotEmpty();
        assertThat(time.get().getHour()).isEqualTo(12);
        assertThat(time.get().getMinute()).isEqualTo(30);
    }

    @Test
    public void shouldParseTimeWithSemicolon() {
        String timeWithSemicolon = "12;30";

        Optional<LocalTime> time = TimeParser.parseToLocalTime(timeWithSemicolon);

        assertThat(time).isNotEmpty();
        assertThat(time.get().getHour()).isEqualTo(12);
        assertThat(time.get().getMinute()).isEqualTo(30);
    }

    @Test
    public void shouldParseTimeWithHyphen() {
        String timeWithHyphen = "12-30";

        Optional<LocalTime> time = TimeParser.parseToLocalTime(timeWithHyphen);

        assertThat(time).isNotEmpty();
        assertThat(time.get().getHour()).isEqualTo(12);
        assertThat(time.get().getMinute()).isEqualTo(30);
    }

    @Test
    public void shouldParseTimeWithComma() {
        String timeWithComma = "12,30";

        Optional<LocalTime> time = TimeParser.parseToLocalTime(timeWithComma);

        assertThat(time).isNotEmpty();
        assertThat(time.get().getHour()).isEqualTo(12);
        assertThat(time.get().getMinute()).isEqualTo(30);
    }

    @Test
    public void shouldThrowIllegalArgumentException() {
        String onlyOneNumber = "12";

        assertThrows(IllegalArgumentException.class, () -> TimeParser.parseToLocalTime(onlyOneNumber));
    }

    @Test
    public void shouldNotParse() {
        String timeWithString = "12:ten";

        Optional<LocalTime> time = TimeParser.parseToLocalTime(timeWithString);

        assertThat(time).isEmpty();
    }
}
