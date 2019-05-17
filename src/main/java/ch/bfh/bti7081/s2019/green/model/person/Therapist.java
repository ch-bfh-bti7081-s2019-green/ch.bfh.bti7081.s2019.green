package ch.bfh.bti7081.s2019.green.model.person;

import ch.bfh.bti7081.s2019.green.model.prescription.Prescription;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "THERAPIST")
@PrimaryKeyJoinColumn(name = "ID")
public class Therapist extends Person{
    @OneToMany(mappedBy = "therapist")
    private List<Patient> patients;

    @OneToMany(mappedBy = "therapist")
    private List<Prescription> prescriptions;

    public void addPatient(Patient patient){
        if(patients == null){
            patients = new ArrayList<>();
        }
        patient.setTherapist(this);
        patients.add(patient);
    }

    public List<Patient> getPatients(){
        if(patients == null){
            patients = new ArrayList<>();
        }
        return patients;
    }
}

