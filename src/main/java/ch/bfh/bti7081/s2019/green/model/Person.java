package ch.bfh.bti7081.s2019.green.model;

import ch.bfh.bti7081.s2019.green.persistence.converters.LocalDateConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "PERSON")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person extends AbstractBaseEntity{
    @Column(name = "AHV")
    private Integer ahvNumber;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "user_name")
    private String username;

    @Column(name = "birth_date")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate birthdate;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Contact contactData;

    public Person(){
        // explicit empty constructor for hibernate
    }
}
