import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class EveryTimeUnitRecurrenceTest {

    private static final LocalDate DATE_A = LocalDate.of(2019, 4, 25);

    private static final LocalTime TIME_A = LocalTime.of(14, 33);

    private static final ZoneId ZONE_EU_WEST = ZoneId.of("Europe/Berlin");

    @Test
    public void testEvery3Days(){
        ZonedDateTime current = ZonedDateTime.of(DATE_A, TIME_A, ZONE_EU_WEST);
        Recurrence recurrence = new EveryTimeUnitRecurrence(ChronoUnit.DAYS, 3);
        ZonedDateTime expected = ZonedDateTime.of(DATE_A.plusDays(3), TIME_A, ZONE_EU_WEST);

        Assert.assertEquals(expected, recurrence.calculateNext(current));
    }

    @Test
    public void testEvery12Hours(){
        ZonedDateTime current = ZonedDateTime.of(DATE_A, TIME_A, ZONE_EU_WEST);
        Recurrence recurrence = new EveryTimeUnitRecurrence(ChronoUnit.HOURS, 12);
        ZonedDateTime expected = ZonedDateTime.of(DATE_A, TIME_A, ZONE_EU_WEST).plusHours(12);

        current = recurrence.calculateNext(current);
        Assert.assertEquals(expected, current);

        current = recurrence.calculateNext(current);
        expected = expected.plusHours(12);
        Assert.assertEquals(expected, current);
    }
}
