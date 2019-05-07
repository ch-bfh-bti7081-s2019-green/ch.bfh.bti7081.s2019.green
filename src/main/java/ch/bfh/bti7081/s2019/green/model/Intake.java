package ch.bfh.bti7081.s2019.green.model;

import ch.bfh.bti7081.s2019.green.persistence.converters.ZonedDateTimeConverter;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
//TODO SQL
public class Intake extends AbstractBaseEntity{
    @Column(name = "TIME")
    @Convert(converter = ZonedDateTimeConverter.class)
    ZonedDateTime time;

    @OneToMany
    @JoinColumn(name = "PRESCRIPTION_ID")
    Prescription prescription;
}
