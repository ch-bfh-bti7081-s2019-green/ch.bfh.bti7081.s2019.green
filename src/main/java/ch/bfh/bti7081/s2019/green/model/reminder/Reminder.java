package ch.bfh.bti7081.s2019.green.model.reminder;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import ch.bfh.bti7081.s2019.green.model.prescription.Prescription;
import ch.bfh.bti7081.s2019.green.persistence.converters.ZonedDateTimeConverter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

public class Reminder extends AbstractBaseEntity {
    @OneToOne(mappedBy = "reminder")
    private Prescription prescription;

    @Column(name = "NOTIFICATION_TIME")
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime notificationTime;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "DEFER_TIME")
    @Convert(converter = ZonedDateTimeConverter.class)
    @JoinTable(name = "DEFER_TIMES", joinColumns = @JoinColumn(name = "REMINDER_ID", referencedColumnName = "ID"))
    private List<ZonedDateTime> deferTimes;

    @OneToMany(mappedBy = "reminder")
    private List<Recurrence> recurrences;
}
