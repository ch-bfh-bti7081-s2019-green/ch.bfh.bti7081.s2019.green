package ch.bfh.bti7081.s2019.green.model.person;

import ch.bfh.bti7081.s2019.green.model.prescription.Prescription;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "THERAPIST")
public class Therapist extends Person{
    @OneToMany(mappedBy = "therapist")
    private List<Patient> patients;

    @OneToMany(mappedBy = "therapist")
    private List<Prescription> prescriptions;
}
