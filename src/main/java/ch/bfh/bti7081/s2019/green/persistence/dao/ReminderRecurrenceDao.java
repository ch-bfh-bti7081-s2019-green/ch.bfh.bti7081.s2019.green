package ch.bfh.bti7081.s2019.green.persistence.dao;

import ch.bfh.bti7081.s2019.green.model.reminder.ReminderRecurrence;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class ReminderRecurrenceDao extends AbstractDao<ReminderRecurrence> {

    private final SessionSingleton db = SessionSingleton.getInstance();

    public ReminderRecurrenceDao() {
        super(ReminderRecurrence.class);
    }

    public Optional<List<ReminderRecurrence>> getAllReminders() {
        return db.executeInTransaction(session -> {
            String queryString = "select r from ReminderRecurrence r";
            Query<ReminderRecurrence> query = session.createQuery(queryString, ReminderRecurrence.class);
            return Optional.ofNullable(query.getResultList());
        });
    }

    public void addReminder(ReminderRecurrence reminderRecurrence) {
        db.executeInTransactionNoResult(s -> s.save(reminderRecurrence));
    }

    public void removeReminder(ReminderRecurrence reminderRecurrence) {
        db.executeInTransactionNoResult(s -> s.remove(reminderRecurrence));
    }

}
