package ch.bfh.bti7081.s2019.green.model.prescription;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "MEDICATION")
public class Medication extends AbstractBaseEntity {
    @Column(name = "NAME")
    private String name;

    @Column(name = "EAN")
    private Integer EAN;
}
