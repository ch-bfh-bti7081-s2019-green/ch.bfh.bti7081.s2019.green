package ch.bfh.bti7081.s2019.green.model.prescription;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import ch.bfh.bti7081.s2019.green.model.person.Patient;
import ch.bfh.bti7081.s2019.green.model.person.Therapist;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "PRESCRIPTION")
public class Prescription extends AbstractBaseEntity {

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
    private LocalDate issueDate;

    @Column(name = "VALID_UNTIL")
    private LocalDate validUntil;

    @Column(name = "FIRST_INTAKE")
    private OffsetDateTime firstIntake;
}
