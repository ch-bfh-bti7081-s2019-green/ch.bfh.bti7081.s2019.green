package ch.bfh.bti7081.s2019.green.model.person;

import ch.bfh.bti7081.s2019.green.model.diary.MoodDiary;
import ch.bfh.bti7081.s2019.green.model.prescription.Intake;
import ch.bfh.bti7081.s2019.green.model.prescription.Prescription;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"diary", "therapist", "prescriptions"})
@Entity
@Table(name = "PATIENT")
public class Patient extends Person {
    @ManyToOne
    @JoinColumn(name = "THERAPIST_ID")
    private Therapist therapist;

    @ManyToOne(targetEntity = Person.class)
    @JoinTable(name = "EMERGENCY_CONTACT",
            joinColumns = @JoinColumn(name = "PATIENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "EC_PERSON_ID", referencedColumnName = "ID"))
    private List<Person> emergencyContacts;

    @OneToOne
    @JoinColumn(name = "DIARY_ID")
    private MoodDiary diary;

    @OneToMany(mappedBy = "patient")
    private List<Prescription> prescriptions;

    @OneToMany(mappedBy = "patient")
    private List<Intake> intakes;

    public void setDiary(MoodDiary diary) {
        this.diary = diary;
        diary.setPatient(this);
    }

    public boolean hasDiary() {
        return diary != null;
    }
}
