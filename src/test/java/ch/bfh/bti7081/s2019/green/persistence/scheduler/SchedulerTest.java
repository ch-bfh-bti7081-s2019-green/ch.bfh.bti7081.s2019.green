package ch.bfh.bti7081.s2019.green.persistence.scheduler;

import ch.bfh.bti7081.s2019.green.scheduler.Scheduler;
import org.junit.Assert;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.ScheduledFuture;

public class SchedulerTest {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Test(timeout = 5000)
    public void testSingleSchedule() throws Exception{
        final ZonedDateTime startTime = ZonedDateTime.now();
        final ZonedDateTime scheduledTime = startTime.plusSeconds(3);
        Scheduler scheduler = Scheduler.getInstance();
        ScheduledFuture<ZonedDateTime> future = scheduler.schedule(scheduledTime, ZonedDateTime::now);
        ZonedDateTime endTime = future.get();

        assertTimeEqualsWithMargin(endTime, scheduledTime, 1, ChronoUnit.SECONDS);
    }

    @Test(timeout = 10000)
    public void testMultiSchedule() throws Exception{
        final ZonedDateTime startTime = ZonedDateTime.now();
        final ZonedDateTime scheduledTimeOne = startTime.plusSeconds(3);
        final ZonedDateTime scheduledTimeTwo = startTime.plusSeconds(5);

        Scheduler scheduler = Scheduler.getInstance();

        ScheduledFuture<ZonedDateTime> futureOne = scheduler.schedule(scheduledTimeOne, ZonedDateTime::now);
        ScheduledFuture<ZonedDateTime> futureTwo = scheduler.schedule(scheduledTimeTwo, ZonedDateTime::now);

        ZonedDateTime endTimeOne = futureOne.get();
        ZonedDateTime endTimeTwo = futureTwo.get();

        assertTimeEqualsWithMargin(endTimeOne, scheduledTimeOne, 1, ChronoUnit.SECONDS);
        assertTimeEqualsWithMargin(endTimeTwo, scheduledTimeTwo, 1, ChronoUnit.SECONDS);
    }

    @Test(timeout = 500)
    public void testScheduleInThePast() throws Exception{
        final ZonedDateTime startTime = ZonedDateTime.now();
        final ZonedDateTime scheduledTime = startTime.minusSeconds(3);
        Scheduler scheduler = Scheduler.getInstance();
        ScheduledFuture<ZonedDateTime> future = scheduler.schedule(scheduledTime, ZonedDateTime::now);
        ZonedDateTime endTime = future.get();

        assertTimeEqualsWithMargin(endTime, startTime, 1, ChronoUnit.SECONDS);
    }

    private void assertTimeEqualsWithMargin(ZonedDateTime actual, ZonedDateTime expected, long i, TemporalUnit unit){
        Assert.assertTrue(String.format("%s should be AFTER %s within a margin of %d %s", actual.format(FMT), expected.format(FMT), i, unit), actual.isAfter(expected.minus(i, unit)));
        Assert.assertTrue(String.format("%s should be BEFORE %s within a margin of %d %s", actual.format(FMT), expected.format(FMT), i, unit), actual.isBefore(expected.plus(i, unit)));
    }
}
