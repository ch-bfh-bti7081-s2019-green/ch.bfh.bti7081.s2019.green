package ch.bfh.bti7081.s2019.green.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "PATIENT")
public class Patient extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "THERAPIST_ID")
    private Therapist therapist;

    @Transient //TODO model emergency contacts (with a relation table)
    private List<Person> emergencyContacts;

    //TODO implement MoodDiary
    //private Optional<MoodDiary> diary;

    //TODO SQL
    @OneToMany(mappedBy = "patient")
    private List<Prescription> prescriptions;

    //private List<Intake> intakes;
}
