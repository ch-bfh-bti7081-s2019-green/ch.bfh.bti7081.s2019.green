package ch.bfh.bti7081.s2019.green.model.reminder;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

//TODO MH implement actual instances of Recurrence
//TODO MH create DB Schema and tests depending on Inheritance strategy chosen
//Inheritance -> https://thoughts-on-java.org/complete-guide-inheritance-strategies-jpa-hibernate/
@MappedSuperclass
public abstract class Recurrence extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "REMINDER_ID")
    private Reminder reminder;

    public void setReminder(Reminder reminder){
        this.reminder = reminder;
    }

    /**
     * Takes a reminder and calculates the "next instance" of that reminder.
     * Taking into account the entire list of recurrences in the reminder;
     */
    public static Reminder calculateNext(Reminder reminder) {
        throw new RuntimeException("Not yet implemented, have fun Christian");
    }

    public static String toDisplayString() {
        throw new RuntimeException("Not yet implemented, have fun Christian");
    }
}
