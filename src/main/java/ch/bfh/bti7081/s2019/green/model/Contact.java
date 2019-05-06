package ch.bfh.bti7081.s2019.green.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CONTACT")
public class Contact {

    @Id
    @Column(name = "ID")
    private Long id;

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

    public Contact(){
        // explicit empty constructor for hibernate
    }
}