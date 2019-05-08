package ch.bfh.bti7081.s2019.green.model;

import ch.bfh.bti7081.s2019.green.persistence.converters.LocalTimeConverter;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ACTIVITIES")
public class Activity {

    @Id
    @Column(name = "ID")
    private Long id;

    @Enumerated
    @Column(columnDefinition = "smallint")
    private ActivityType type;

    @Column(name = "TIME")
    @Convert(converter = LocalTimeConverter.class)
    private LocalTimeConverter time;

    @Column(name = "TEXT")
    private String text;

    public Activity() {
        // explicit empty constructor for hibernate
    }
}
