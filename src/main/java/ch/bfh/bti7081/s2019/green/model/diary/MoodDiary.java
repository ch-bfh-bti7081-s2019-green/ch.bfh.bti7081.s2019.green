package ch.bfh.bti7081.s2019.green.model.diary;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import ch.bfh.bti7081.s2019.green.model.person.Patient;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "MOOD_DIARIES")
public class MoodDiary extends AbstractBaseEntity {
    @OneToOne
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    @OneToMany(mappedBy = "diary")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Entry> entries;


    public MoodDiary() {
        // explicit empty constructor for hibernate
    }
}
