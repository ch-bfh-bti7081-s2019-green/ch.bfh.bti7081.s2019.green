package ch.bfh.bti7081.s2019.green.model.diary;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"entry"})
@Entity
@Table(name = "ACTIVITIES")
public class Activity extends AbstractBaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private ActivityType type;

    @Column(name = "TIME")
    private LocalTime time;

    @Column(name = "TEXT")
    private String text;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entry")
    private Entry entry;

    public Activity() {
        // explicit empty constructor for hibernate
    }

    public Activity(ActivityType type) {
        this.type = type;
    }
}
