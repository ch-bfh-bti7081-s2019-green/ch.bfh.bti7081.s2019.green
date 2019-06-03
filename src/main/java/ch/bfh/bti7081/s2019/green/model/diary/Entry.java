package ch.bfh.bti7081.s2019.green.model.diary;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import ch.bfh.bti7081.s2019.green.persistence.converters.LocalDateConverter;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"diary", "activities"})
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DIARY_ID")
    private MoodDiary diary;

    @OneToMany(mappedBy = "entry")
    private List<Activity> activities;

    @Column(name = "NOTES")
    private String notes;

    public void addActivity(Activity activity) {
        if (activities == null) {
            activities = new ArrayList<>();
        }
        activities.add(activity);
        activity.setEntry(this);
    }

    public List<Activity> getActivities() {
        if (activities == null) {
            return new ArrayList<>();
        }

        return activities;
    }

    public void removeActivity(Activity activity) {
        if (activities == null) {
            activities = new ArrayList<>();
        }
        activities.remove(activity);
        activity.setEntry(null);
    }

    public Entry() {
        // explicit empty constructor for hibernate
    }

    public void setMood(double mood) {
        this.mood = (int) mood;
    }

    public double getMood() {
        return (double) this.mood;
    }

    public void getSleepHours(double sleepHours) {
        this.sleepHours = (double) sleepHours;
    }

    public double setSleepHours() {
        return (double) this.sleepHours;
    }

    public void getNotes(String notes) {
        this.notes = (String) notes;
    }

    public String setNotes() {
        return (String) this.notes;
    }
}
