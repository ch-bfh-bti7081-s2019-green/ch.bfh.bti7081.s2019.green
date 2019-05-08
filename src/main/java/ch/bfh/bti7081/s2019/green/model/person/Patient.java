package ch.bfh.bti7081.s2019.green.model.person;

import ch.bfh.bti7081.s2019.green.model.prescription.Prescription;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "PATIENT")
public class Patient extends Person {
    @ManyToOne
    @JoinColumn(name = "THERAPIST_ID")
    private Therapist therapist;

    @ManyToOne
    @JoinTable(name = "EMERGENCY_CONTACT",
            joinColumns = @JoinColumn(name = "PATIENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "EC_PERSON_ID", referencedColumnName = "ID"))
    private List<Person> emergencyContacts;

    //TODO implement MoodDiary
    //private Optional<MoodDiary> diary;

    @OneToMany(mappedBy = "patient")
    private List<Prescription> prescriptions;

    //private List<Intake> intakes;
}
