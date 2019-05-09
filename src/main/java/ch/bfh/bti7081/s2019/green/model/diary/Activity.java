package ch.bfh.bti7081.s2019.green.model.diary;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import ch.bfh.bti7081.s2019.green.persistence.converters.LocalTimeConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "ACTIVITIES")
public class Activity extends AbstractBaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private ActivityType type;

    @Column(name = "TIME")
    @Convert(converter = LocalTimeConverter.class)
    private LocalTime time;

    @Column(name = "TEXT")
    private String text;

    @ManyToOne
    @JoinColumn(name = "entry")
    private Entry entry;

    public Activity() {
        // explicit empty constructor for hibernate
    }
}