package ch.bfh.bti7081.s2019.green.persistence.seed;

import ch.bfh.bti7081.s2019.green.AuthService;
import ch.bfh.bti7081.s2019.green.model.person.Contact;
import ch.bfh.bti7081.s2019.green.model.person.Patient;
import ch.bfh.bti7081.s2019.green.model.person.Therapist;
import ch.bfh.bti7081.s2019.green.model.prescription.Dose;
import ch.bfh.bti7081.s2019.green.model.prescription.Medication;
import ch.bfh.bti7081.s2019.green.model.prescription.Prescription;
import ch.bfh.bti7081.s2019.green.model.prescription.Unit;
import ch.bfh.bti7081.s2019.green.model.reminder.Reminder;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class DatabaseSeederService {


    private Faker faker = new Faker();

    public Patient getRandomPatient() {
        Patient patient = new Patient();
        patient.setFirstName(faker.name().firstName());
        patient.setLastName(faker.name().lastName());
        patient.setPassword(AuthService.getEncodedPassword("password"));
        patient.setAhvNumber((int) faker.number().randomNumber(13, true));
        patient.setBirthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        patient.setUsername(faker.name().username());

        return patient;
    }

    public Therapist getRandomTherapist() {
        Therapist therapist = new Therapist();
        therapist.setFirstName(faker.name().firstName());
        therapist.setLastName(faker.name().lastName());
        therapist.setPassword(AuthService.getEncodedPassword("password"));
        therapist.setAhvNumber((int) faker.number().randomNumber(13, true));
        therapist.setBirthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        therapist.setUsername(faker.name().username());
        Contact contactData = getRandomContact();
        contactData.setPerson(therapist);
        therapist.setContactData(contactData);


        return therapist;
    }

    public Contact getRandomContact() {
        Contact contact = new Contact();
        contact.setCity(faker.address().cityName());
        contact.setCountry(faker.address().country());
        contact.setStreet(faker.address().streetAddress());
        contact.setEmail(faker.internet().emailAddress());
        contact.setPhone(faker.phoneNumber().phoneNumber());

        return contact;
    }

    public Prescription getRandomPrescription() {
        Prescription prescription = new Prescription();
        prescription.setFirstIntake(OffsetDateTime.now());
        prescription.setIssueDate(LocalDate.now());
        prescription.setValidUntil(LocalDate.of(2022, 12, 25));
        return prescription;
    }

    //does not have dependencies to other table
    public Dose getRandomDose() {
        Dose dose = new Dose();
        dose.setAmount(faker.number().numberBetween(1, 5));
        dose.setUnit(Unit.PILL);
        return dose;
    }

    //does not have dependencies to other table
    public Medication getRandomMedication() {
        Medication medication = new Medication();
        medication.setEAN(5);
        medication.setName(faker.funnyName().name());
        return medication;
    }

    public Reminder getRandomReminder() {
        Reminder reminder = new Reminder();
        reminder.setDeferTimes(new ArrayList<>());
        reminder.setNotificationTime(OffsetDateTime.now());
        return reminder;
    }


}
