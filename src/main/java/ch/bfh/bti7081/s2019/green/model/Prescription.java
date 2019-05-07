package ch.bfh.bti7081.s2019.green.model;

import ch.bfh.bti7081.s2019.green.persistence.converters.LocalDateConverter;
import ch.bfh.bti7081.s2019.green.persistence.converters.ZonedDateTimeConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@Entity
//TODO SQL
public class Prescription extends AbstractBaseEntity{

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "THERAPIST_ID")
    private Therapist therapist;

    @OneToOne
    @JoinColumn(name = "MEDICATION_ID")
    private Medication medication;

    @OneToOne
    @JoinColumn(name = "DOSE_ID")
    private Dose dose;

    @Column(name = "ISSUE_DATE")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate issueDate;

    @Column(name = "VALID_UNTIL")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate validUntil;

    @OneToMany(mappedBy = "prescription")
    private Reminder reminder;

    @Column(name = "FIRST_INTAKE")
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime firstIntake;
}
