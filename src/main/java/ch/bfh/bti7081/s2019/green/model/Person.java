package ch.bfh.bti7081.s2019.green.model;

import ch.bfh.bti7081.s2019.green.persistence.converters.LocalDateConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "PERSON")
public class Person {

    @Id
    @Column(name = "AHV")
    private Long ahvNumber;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "BIRTH_DATE")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate birthDate;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Contact contactData;

    public Person() {
        // explicit empty constructor for hibernate
    }
}
