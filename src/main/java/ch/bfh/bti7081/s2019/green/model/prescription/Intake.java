package ch.bfh.bti7081.s2019.green.model.prescription;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import ch.bfh.bti7081.s2019.green.persistence.converters.ZonedDateTimeConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "INTAKE")
public class Intake extends AbstractBaseEntity {
    @Column(name = "TIME")
    @Convert(converter = ZonedDateTimeConverter.class)
    ZonedDateTime time;

    @OneToMany
    @JoinColumn(name = "PRESCRIPTION_ID")
    List<Prescription> prescription;
}
