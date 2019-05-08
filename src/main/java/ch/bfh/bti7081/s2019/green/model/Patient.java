package ch.bfh.bti7081.s2019.green.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Optional;

@Data
@Entity
public class Patient extends Person {
    @OneToMany(mappedBy = "PATIENT")
    private MoodDiary diary;

    public Patient() {
        // Explicit empty constructor for hibernate
    }
}
