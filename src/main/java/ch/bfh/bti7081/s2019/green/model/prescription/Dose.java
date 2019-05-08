package ch.bfh.bti7081.s2019.green.model.prescription;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DOSE")
public class Dose extends AbstractBaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "UNIT")
    private Unit unit;

    @Column(name = "AMOUNT")
    private Integer amount;
}
