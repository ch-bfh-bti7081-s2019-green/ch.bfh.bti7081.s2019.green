package ch.bfh.bti7081.s2019.green.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
//TODO SQL
public class Dose extends AbstractBaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(name = "UNIT")
    private Unit unit;

    @Column(name = "AMOUNT")
    private Integer amount;
}
