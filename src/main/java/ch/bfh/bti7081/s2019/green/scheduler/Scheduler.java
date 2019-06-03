package ch.bfh.bti7081.s2019.green.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

/**
 * Enables scheduling of Callables at a ZonedDateTime.
 * Usually accurate to about a second.
 * Keeps a pool of 10 threads, which will automatically expand when needed.
 */
public class Scheduler {
    private final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);
    private static Scheduler instance;
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final ScheduledExecutorService executorService;

    public static Scheduler getInstance() {
        if (instance == null) {
            instance = new Scheduler();
        }
        return instance;
    }

    private Scheduler() {
        executorService = Executors.newScheduledThreadPool(10);
    }

    /**
     * Schedules the Callable for the triggerTime.
     * Accurate to within about a second.
     * If the triggerTime is in the past, the callable will be run as soon as possible.
     *
     * @return A ScheduledFuture for cancelling or getting the result of the callable.
     */
    public <T> ScheduledFuture<T> schedule(ZonedDateTime triggerTime, Callable<T> callable) {
        final ZonedDateTime now = ZonedDateTime.now();

        Duration untilTriggerTime = Duration.between(now, triggerTime);

        if (triggerTime.isBefore(now)) {
            LOGGER.info("Task scheduled for immediate execution");
        } else {
            LOGGER.info("Task scheduled for {}", triggerTime.format(FMT));
        }

        return executorService.schedule(callable, untilTriggerTime.getSeconds(), TimeUnit.SECONDS);
    }
}
