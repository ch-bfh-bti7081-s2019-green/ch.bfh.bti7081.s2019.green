package ch.bfh.bti7081.s2019.green.persistence.dao;

import ch.bfh.bti7081.s2019.green.model.reminder.Reminder;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class ReminderDao extends AbstractDao<Reminder> {

    private final SessionSingleton db = SessionSingleton.getInstance();

    public ReminderDao() {
        super(Reminder.class);
    }

    public void addReminder(Reminder reminder) {
        db.executeInTransactionNoResult(s -> s.save(reminder));
    }

    public void removeReminder(Reminder reminder) {
        db.executeInTransactionNoResult(s -> s.remove(reminder));
    }
}
