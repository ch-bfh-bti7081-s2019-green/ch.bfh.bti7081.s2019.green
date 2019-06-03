package ch.bfh.bti7081.s2019.green.persistence.dao;

import ch.bfh.bti7081.s2019.green.model.prescription.Prescription;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PrescriptionDao extends AbstractDao<Prescription> {
    private final SessionSingleton db = SessionSingleton.getInstance();

    public PrescriptionDao() {
        super(Prescription.class);
    }

    public Optional<List<Prescription>> getAllPrescriptions() {
        return db.executeInTransaction(session -> {
            String queryString = "select r from Prescription r";
            Query<Prescription> query = session.createQuery(queryString, Prescription.class);
            return Optional.ofNullable(query.getResultList());
        });
    }

    public void addPrescription(Prescription prescription) {
        db.executeInTransactionNoResult(s -> s.save(prescription));
    }

    public void removePrescription(Prescription prescription) {
        db.executeInTransactionNoResult(s -> s.remove(prescription));
    }
}
