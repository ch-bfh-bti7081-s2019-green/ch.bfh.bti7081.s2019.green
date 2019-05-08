package ch.bfh.bti7081.s2019.green.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "PATIENT")
public class Patient extends Person {
    @OneToOne
    @JoinColumn(name = "diary")
    private MoodDiary diary;

    public Patient() {
        super();
        // Explicit empty constructor for hibernate
    }
}
