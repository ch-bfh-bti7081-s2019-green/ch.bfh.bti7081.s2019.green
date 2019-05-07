package ch.bfh.bti7081.s2019.green.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
//TODO SQL
public class Medication extends AbstractBaseEntity{
    @Column(name = "NAME")
    private String name;

    @Column(name = "EAN")
    private Integer EAN;
}
