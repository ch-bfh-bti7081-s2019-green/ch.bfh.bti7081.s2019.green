import org.junit.Assert;
import org.junit.Test;

import java.time.*;

public class AtTimeRecurrenceTest {

    private static final LocalDate DATE_A = LocalDate.of(2019, 4, 25);

    private static final LocalTime TIME_A = LocalTime.of(14, 33);

    private static final ZoneId ZONE_EU_WEST = ZoneId.of("Europe/Berlin");

    @Test
    public void testAt1500(){
        ZonedDateTime current = ZonedDateTime.of(DATE_A, TIME_A, ZONE_EU_WEST);
        Recurrence recurrence = new AtTimeRecurrence(LocalTime.of(15,0));
        ZonedDateTime expected = ZonedDateTime.of(DATE_A, LocalTime.of(15, 0), ZONE_EU_WEST);

        Assert.assertEquals(expected, recurrence.calculateNext(current));
    }

}
