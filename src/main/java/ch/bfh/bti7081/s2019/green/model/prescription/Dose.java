package ch.bfh.bti7081.s2019.green.model.prescription;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "DOSE")
public class Dose extends AbstractBaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "UNIT")
    private Unit unit;

    @Column(name = "AMOUNT")
    private Integer amount;
}
