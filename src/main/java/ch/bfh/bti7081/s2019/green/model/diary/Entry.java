package ch.bfh.bti7081.s2019.green.model.diary;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = {"diary", "activities"})
@Entity
@Table(name = "ENTRIES")
public class Entry extends AbstractBaseEntity {
    @Column(name = "DATE")
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

    public Entry() {
    }

    public void addActivity(Activity activity) {
        if (activities == null) {
            activities = new ArrayList<>();
        }

        activities.add(activity);
        activity.setEntry(this);
    }

    public List<Activity> getActivities() {
        if (activities == null) {
            activities = new ArrayList<>();
        }

        return activities;
    }

    public List<Activity> getActivitiesByType(ActivityType activityType) {
        return getActivities().stream().filter(a -> a.getType() == activityType).collect(Collectors.toList());
    }

    public void removeActivity(Activity activity) {
        if (activities == null) {
            activities = new ArrayList<>();
        }
        activities.remove(activity);
        activity.setEntry(null);
    }

    public double getMood() {
        return (double) this.mood;
    }

    public void setMood(double mood) {
        this.mood = (int) mood;
    }
}
