package ch.bfh.bti7081.s2019.green.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity()
@Table(name = "PERSON")
public class Person {

    @Id
    @Column(name = "AHV")
    private Long ahvNumber;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "user_name")
    private String username;

    @Column(name = "birth_date")
    private LocalDate birthdate;

    public Person(){
        // explicit empty constructor for hibernate
    }

    public Long getAhvNumber() {
        return ahvNumber;
    }

    public void setAhvNumber(Long ahvNumber) {
        this.ahvNumber = ahvNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
