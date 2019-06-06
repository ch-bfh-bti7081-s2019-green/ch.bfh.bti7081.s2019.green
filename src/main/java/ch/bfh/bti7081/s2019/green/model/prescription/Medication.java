package ch.bfh.bti7081.s2019.green.model.prescription;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "MEDICATION")
public class Medication extends AbstractBaseEntity {
    @Column(name = "NAME")
    private String name;

    @Column(name = "EAN")
    private Integer EAN;
}
