package ch.bfh.bti7081.s2019.green.model.person;

import ch.bfh.bti7081.s2019.green.model.prescription.Prescription;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "THERAPIST")
public class Therapist extends Person {
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
