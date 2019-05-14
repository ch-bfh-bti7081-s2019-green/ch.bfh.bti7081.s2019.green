package ch.bfh.bti7081.s2019.green.model.diary;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import ch.bfh.bti7081.s2019.green.model.person.Patient;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {"entries", "patient"})
@Entity
@Table(name = "MOOD_DIARIES")
public class MoodDiary extends AbstractBaseEntity {
    @OneToOne
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    @OneToMany(mappedBy = "diary")
    @EqualsAndHashCode.Exclude
    private List<Entry> entries;

    public List<Entry> getEntries() {
        if (entries == null) {
            entries = new ArrayList<>();
        }
        return entries;
    }

    public void addEntry(Entry entry) {
        if (entries == null) {
            entries = new ArrayList<>();
        }
        entries.add(entry);
        entry.setDiary(this);
    }

    public void removeEntry(Entry entry) {
        if (entries == null) {
            entries = new ArrayList<>();
        }
        entries.remove(entry);
        entry.setDiary(null);
    }

    public MoodDiary() {
        // explicit empty constructor for hibernate
    }
}
