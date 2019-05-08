package ch.bfh.bti7081.s2019.green.persistence.dao;

import ch.bfh.bti7081.s2019.green.model.diary.MoodDiary;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import org.hibernate.query.Query;

import java.util.Optional;

public class MoodDiaryDao extends AbstractDao<MoodDiary> {
    private final SessionSingleton db = SessionSingleton.getInstance();

    public MoodDiaryDao() {
        super(MoodDiary.class);
    }

    public Optional findByPatient(final Person patient) {
        return db.executeInTransaction(session -> {
            String queryString = "SELECT d FROM MoodDiary d" +
                    " WHERE d.patient = :patient";
            Query<MoodDiary> query = session.createQuery(queryString, MoodDiary.class);
            query.setParameter("patient", patient);

            return Optional.ofNullable(query.uniqueResult());
        });
    }
}
