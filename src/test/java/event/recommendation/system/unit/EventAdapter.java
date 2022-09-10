package event.recommendation.system.unit;

import event.recommendation.system.models.DayOfWeek;
import event.recommendation.system.services.event.util.EventAdapterUtil;
import org.junit.jupiter.api.Test;

import static event.recommendation.system.models.DayOfWeek.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventAdapter {

    @Test
    public void shouldFindDaysByNumber() {
        DayOfWeek friday = EventAdapterUtil.getDayOfWeekByNumber(5);
        assertEquals(friday.getDay(), FRIDAY.getDay());

        DayOfWeek monday = EventAdapterUtil.getDayOfWeekByNumber(1);
        assertEquals(monday.getDay(), MONDAY.getDay());

        DayOfWeek sunday = EventAdapterUtil.getDayOfWeekByNumber(7);
        assertEquals(sunday.getDay(), SUNDAY.getDay());

        DayOfWeek saturday = EventAdapterUtil.getDayOfWeekByNumber(6);
        assertEquals(saturday.getDay(), SATURDAY.getDay());
    }

    @Test
    public void shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> EventAdapterUtil.getDayOfWeekByNumber(8));
        assertThrows(IllegalArgumentException.class, () -> EventAdapterUtil.getDayOfWeekByNumber(0));
    }
}
