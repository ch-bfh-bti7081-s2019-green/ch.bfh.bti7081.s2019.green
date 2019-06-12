package ch.bfh.bti7081.s2019.green.model.reminder;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import ch.bfh.bti7081.s2019.green.model.prescription.Prescription;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "REMINDER")
public class Reminder extends AbstractBaseEntity {

    @ManyToOne
    private Prescription prescription;

    @Column(name = "NOTIFICATION_TIME")
    private OffsetDateTime notificationTime;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "DEFER_TIME")
    @JoinTable(name = "DEFER_TIMES", joinColumns = @JoinColumn(name = "REMINDER_ID", referencedColumnName = "ID"))
    private List<OffsetDateTime> deferTimes;

    @OneToMany(mappedBy = "reminder", cascade = CascadeType.REMOVE)
    private List<ReminderRecurrence> recurrences;
}
