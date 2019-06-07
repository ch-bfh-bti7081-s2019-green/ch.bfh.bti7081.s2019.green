package ch.bfh.bti7081.s2019.green.model.person;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "person")
@Entity
@Table(name = "CONTACT")
public class Contact extends AbstractBaseEntity {

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "STREET")
    private String street;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @OneToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    public Contact() {
        // explicit empty constructor for hibernate
    }
}
