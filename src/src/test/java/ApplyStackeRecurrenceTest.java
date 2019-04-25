import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class ApplyStackeRecurrenceTest {
    @Test
    public void testStackedAtTimeEveryTimeUnitRecurrence(){
        ZonedDateTime current = ZonedDateTime.of(TestData.DATE_A, TestData.TIME_A, TestData.ZONE_EU_WEST);
        Recurrence atTimeRecurrence = new AtTimeRecurrence(LocalTime.of(10, 20));
        Recurrence everyTimeUnitRecurrence = new EveryTimeUnitRecurrence(ChronoUnit.DAYS, 2);
        ZonedDateTime expected = ZonedDateTime.of(TestData.DATE_A.plusDays(2), LocalTime.of(10, 20), TestData.ZONE_EU_WEST);

        ZonedDateTime result = RecurrenceStacker.applyStacked(current, atTimeRecurrence, everyTimeUnitRecurrence);

        Assert.assertEquals(expected, result);

    }
}
