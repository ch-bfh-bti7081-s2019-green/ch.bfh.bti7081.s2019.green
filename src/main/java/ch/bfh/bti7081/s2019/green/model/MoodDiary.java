package ch.bfh.bti7081.s2019.green.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "MOOD_DIARIES")
public class MoodDiary {

    @Id
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "patient")
    private Patient patient;

    @OneToMany(mappedBy = "diary")
    private List<Entry> entries;


    public MoodDiary(){
        // explicit empty constructor for hibernate
    }
}
