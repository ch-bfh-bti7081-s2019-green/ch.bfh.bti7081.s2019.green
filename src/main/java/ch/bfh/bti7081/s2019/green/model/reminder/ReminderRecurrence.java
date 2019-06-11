package ch.bfh.bti7081.s2019.green.model.reminder;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Duration;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "REMINDER_RECURRENCE")
public class ReminderRecurrence extends Recurrence {
    @Column(name = "duration")
    private Duration duration;
}
