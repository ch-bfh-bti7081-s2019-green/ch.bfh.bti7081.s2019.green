package ch.bfh.bti7081.s2019.green.model.person;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import ch.bfh.bti7081.s2019.green.persistence.converters.LocalDateConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "PERSON")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person extends AbstractBaseEntity {
    @Column(name = "AHV")
    private Integer ahvNumber;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "BIRTH_DATE")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate birthDate;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Contact contactData;

    public Person() {
        // explicit empty constructor for hibernate
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
}
