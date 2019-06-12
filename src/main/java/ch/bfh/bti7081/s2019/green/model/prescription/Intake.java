package ch.bfh.bti7081.s2019.green.model.prescription;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import ch.bfh.bti7081.s2019.green.model.person.Patient;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.OffsetDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "INTAKE")
public class Intake extends AbstractBaseEntity {
    @Column(name = "TIME")
    private OffsetDateTime time;

    @ManyToOne
    @JoinColumn(name = "PRESCRIPTION_ID")
    private Prescription prescription;

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

}
