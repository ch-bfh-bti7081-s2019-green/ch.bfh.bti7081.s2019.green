package ch.bfh.bti7081.s2019.green.chat;

import ch.bfh.bti7081.s2019.green.model.person.Patient;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.model.person.Therapist;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;

import java.time.LocalDate;
import java.util.Optional;

public class AuthServiceMock {

    private static Patient person;
    private static SessionSingleton db = SessionSingleton.getInstance();

    static {
        person = db.executeInTransaction(session -> Optional.ofNullable(session.find(Patient.class, 1))).orElseGet(() -> {
            Patient pat = new Patient();
            pat.setAhvNumber(1234);
            pat.setBirthDate(LocalDate.of(1983, 5, 12));
            pat.setFirstName("John");
            pat.setLastName("Doe");
            pat.setUsername("john.doe");
            db.save(pat);

            Therapist therapist = new Therapist();
            therapist.addPatient(pat);
            therapist.setAhvNumber(9876);
            therapist.setBirthDate(LocalDate.of(1972, 12, 3));
            therapist.setFirstName("Harold");
            therapist.setLastName("Green");
            therapist.setUsername("Dr. Green");

            db.save(therapist);

            pat.setTherapist(therapist);
            db.save(pat);

            return pat;
        });
    }

    /*
     */

    public static Person getCurrentUser(){
        return person;
    }
}
