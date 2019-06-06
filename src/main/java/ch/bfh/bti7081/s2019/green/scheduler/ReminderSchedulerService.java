package ch.bfh.bti7081.s2019.green.scheduler;

import ch.bfh.bti7081.s2019.green.model.reminder.Reminder;
import ch.bfh.bti7081.s2019.green.model.reminder.ReminderRecurrence;
import com.vaadin.flow.component.notification.Notification;

import java.util.HashMap;
import java.util.concurrent.ScheduledFuture;

public class ReminderSchedulerService {

    private static volatile ReminderSchedulerService instance;
    private HashMap<String, ScheduledFuture> schedulerMap;

    private ReminderSchedulerService() {
        schedulerMap = new HashMap<>();
    }

    public static ReminderSchedulerService getInstance() {
        if (instance == null) {
            instance = new ReminderSchedulerService();
        }
        return instance;
    }

    public void addReminder(Reminder reminder) {
        addEntryToSchedulerMap(reminder, null, true);
        for (ReminderRecurrence recurrence : reminder.getRecurrences()) {
            addEntryToSchedulerMap(reminder, recurrence, false);
        }
    }

    public void removeReminder(Reminder reminder) {
        String reminderID = this.getIDForSchedulerMap(reminder, null);
        this.cancel(reminderID);
        for (ReminderRecurrence recurrence : reminder.getRecurrences()) {
            this.cancel(this.getIDForSchedulerMap(reminder, recurrence));
        }
    }

    private void addEntryToSchedulerMap(Reminder reminder, ReminderRecurrence recurrence, boolean isBaseReminderEntry) {
        String id = this.getIDForSchedulerMap(reminder, recurrence);
        this.cancel(id);
        if (isBaseReminderEntry) {
            schedulerMap.put(id, Scheduler.getInstance().schedule(reminder.getNotificationTime(), () -> {
                Notification.show("This is a reminder to take your prescription: " + reminder.getPrescription().toString());
                this.reschedule(reminder, recurrence, isBaseReminderEntry);
                return null;
            }));
        } else {
            //TODO rechedules always from reminder base time, should reschedule from now plus duration
            schedulerMap.put(id, Scheduler.getInstance().schedule(reminder.getNotificationTime().plus(recurrence.getDuration()), () -> {
                Notification.show("This is a reminder to take your prescription: " + reminder.getPrescription().toString());
                this.reschedule(reminder, recurrence, isBaseReminderEntry);
                return null;
            }));
        }
    }

    private String getIDForSchedulerMap(Reminder reminder, ReminderRecurrence recurrence) {
        if (recurrence != null) {
            return reminder.getId() + "-" + recurrence.getId();
        }
        return reminder.getId().toString();
    }

    private void reschedule(Reminder reminder, ReminderRecurrence recurrence, boolean isBaseReminderEntry) {
        addEntryToSchedulerMap(reminder, recurrence, isBaseReminderEntry);
    }

    private void cancel(String id) {
        ScheduledFuture scheduledFuture = schedulerMap.get(id);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            schedulerMap.remove(id);
        }
    }
}
