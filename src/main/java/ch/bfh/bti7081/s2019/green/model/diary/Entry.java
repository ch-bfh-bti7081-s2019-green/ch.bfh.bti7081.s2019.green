package ch.bfh.bti7081.s2019.green.model.diary;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import ch.bfh.bti7081.s2019.green.persistence.converters.LocalDateConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "ENTRIES")
public class Entry extends AbstractBaseEntity {
    @Column(name = "DATE")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate date;

    @Column(name = "MOOD")
    private int mood;

    @Column(name = "SLEEP_HOURS")
    private double sleepHours;

    @Column(name = "WATER_DRUNK")
    private double waterDrunk;

    @ManyToOne
    @JoinColumn(name = "diary")
    private MoodDiary diary;

    @OneToMany(mappedBy = "entry")
    private List<Activity> activities;

    @Column(name = "notes")
    private String notes;

    public Entry() {
        // explicit empty constructor for hibernate
    }
}
