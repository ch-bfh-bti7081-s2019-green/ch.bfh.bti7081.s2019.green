package ch.bfh.bti7081.s2019.green.model.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.concurrent.*;

/**
 * Enables scheduling of Callables at a ZonedDateTime.
 * Usually accurate to about a second.
 * Keeps a pool of 10 threads, which will automatically expand when needed.
 */
public class Scheduler {
    private final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);
    private static Scheduler instance;
    private final ScheduledExecutorService executorService;

    public static Scheduler getInstance() {
        if(instance == null){
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
     * @return A ScheduledFuture for cancelling or getting the result of the callable.
     * If the triggerTime is in the past, the callable will be run as soon as possible.
     */
    public <T> ScheduledFuture<T> schedule(ZonedDateTime triggerTime, Callable<T> callable) {
        final ZonedDateTime now = ZonedDateTime.now();

        if (triggerTime.isBefore(now)) {
            LOGGER.warn("TriggerTime is in the past! triggerTime={}, now={}", triggerTime.toString(), now.toString());
        }

        Duration untilTriggerTime = Duration.between(now, triggerTime);

        return executorService.schedule(callable, untilTriggerTime.getSeconds(), TimeUnit.SECONDS);
    }
}
