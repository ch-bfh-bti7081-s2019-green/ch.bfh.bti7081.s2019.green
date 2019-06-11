package ch.bfh.bti7081.s2019.green.persistence.scheduler;

import ch.bfh.bti7081.s2019.green.scheduler.Scheduler;
import org.junit.Assert;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;

public class SchedulerTest {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Test(timeout = 5000)
    public void testSingleSchedule() throws Exception {
        final OffsetDateTime startTime = OffsetDateTime.now();
        final OffsetDateTime scheduledTime = startTime.plusSeconds(4);
        Scheduler scheduler = Scheduler.getInstance();
        ScheduledFuture<OffsetDateTime> future = scheduler.schedule(scheduledTime, OffsetDateTime::now);
        OffsetDateTime endTime = future.get();

        assertTimeEqualsWithMargin(endTime, scheduledTime, 1, ChronoUnit.SECONDS);

        Callable<String> c = () -> "Hello World!";
        ScheduledFuture<String> schedule = scheduler.schedule(scheduledTime, c);

        System.out.println(schedule.get());
    }

    @Test(timeout = 10000)
    public void testMultiSchedule() throws Exception {
        final OffsetDateTime startTime = OffsetDateTime.now();
        final OffsetDateTime scheduledTimeOne = startTime.plusSeconds(3);
        final OffsetDateTime scheduledTimeTwo = startTime.plusSeconds(5);

        Scheduler scheduler = Scheduler.getInstance();

        ScheduledFuture<OffsetDateTime> futureOne = scheduler.schedule(scheduledTimeOne, OffsetDateTime::now);
        ScheduledFuture<OffsetDateTime> futureTwo = scheduler.schedule(scheduledTimeTwo, OffsetDateTime::now);

        OffsetDateTime endTimeOne = futureOne.get();
        OffsetDateTime endTimeTwo = futureTwo.get();

        assertTimeEqualsWithMargin(endTimeOne, scheduledTimeOne, 1, ChronoUnit.SECONDS);
        assertTimeEqualsWithMargin(endTimeTwo, scheduledTimeTwo, 1, ChronoUnit.SECONDS);
    }

    @Test(timeout = 500)
    public void testScheduleInThePast() throws Exception {
        final OffsetDateTime startTime = OffsetDateTime.now();
        final OffsetDateTime scheduledTime = startTime.minusSeconds(3);
        Scheduler scheduler = Scheduler.getInstance();
        ScheduledFuture<OffsetDateTime> future = scheduler.schedule(scheduledTime, OffsetDateTime::now);
        OffsetDateTime endTime = future.get();

        assertTimeEqualsWithMargin(endTime, startTime, 1, ChronoUnit.SECONDS);
    }

    private void assertTimeEqualsWithMargin(OffsetDateTime actual, OffsetDateTime expected, long i, TemporalUnit unit) {
        Assert.assertTrue(String.format("%s should be AFTER %s within a margin of %d %s", actual.format(FMT), expected.format(FMT), i, unit), actual.isAfter(expected.minus(i, unit)));
        Assert.assertTrue(String.format("%s should be BEFORE %s within a margin of %d %s", actual.format(FMT), expected.format(FMT), i, unit), actual.isBefore(expected.plus(i, unit)));
    }
}
