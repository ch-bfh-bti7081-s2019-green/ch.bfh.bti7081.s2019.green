import org.junit.Assert;
import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;

import static java.time.DayOfWeek.*;

public class WeekdayRecurrenceTest {

    private static final LocalDate DATE_A = LocalDate.of(2019, 4, 25);

    private static final LocalTime TIME_A = LocalTime.of(14, 33);

    private static final ZoneId ZONE_EU_WEST = ZoneId.of("Europe/Berlin");

    @Test
    public void testEveryFriday(){
        ZonedDateTime current = ZonedDateTime.of(DATE_A, TIME_A, ZONE_EU_WEST);
        Recurrence recurrence = new WeekdayRecurrence(FRIDAY);

        ZonedDateTime expected1 = ZonedDateTime.of(DATE_A, TIME_A, ZONE_EU_WEST).plusDays(1);
        ZonedDateTime expected2 = ZonedDateTime.of(DATE_A, TIME_A, ZONE_EU_WEST).plusDays(8);
        ZonedDateTime expected3 = ZonedDateTime.of(DATE_A, TIME_A, ZONE_EU_WEST).plusDays(15);

        current = recurrence.calculateNext(current);
        Assert.assertEquals(expected1, current);

        current = recurrence.calculateNext(current);
        Assert.assertEquals(expected2, current);

        current = recurrence.calculateNext(current);
        Assert.assertEquals(expected3, current);
    }

    @Test
    public void testEveryThursdayAndSunday(){
        ZonedDateTime current = ZonedDateTime.of(DATE_A, TIME_A, ZONE_EU_WEST);
        Recurrence recurrence = new WeekdayRecurrence(THURSDAY, SUNDAY);

        ZonedDateTime expected1 = ZonedDateTime.of(DATE_A, TIME_A, ZONE_EU_WEST).plusDays(3);
        ZonedDateTime expected2 = ZonedDateTime.of(DATE_A, TIME_A, ZONE_EU_WEST).plusDays(7);
        ZonedDateTime expected3 = ZonedDateTime.of(DATE_A, TIME_A, ZONE_EU_WEST).plusDays(10);

        current = recurrence.calculateNext(current);
        Assert.assertEquals(expected1, current);

        current = recurrence.calculateNext(current);
        Assert.assertEquals(expected2, current);

        current = recurrence.calculateNext(current);
        Assert.assertEquals(expected3, current);
    }
}
