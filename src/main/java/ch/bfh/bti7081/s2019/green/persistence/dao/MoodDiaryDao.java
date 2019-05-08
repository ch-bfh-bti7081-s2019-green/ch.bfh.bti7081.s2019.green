package ch.bfh.bti7081.s2019.green.persistence.dao;

import ch.bfh.bti7081.s2019.green.model.MoodDiary;
import ch.bfh.bti7081.s2019.green.model.Person;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import org.hibernate.query.Query;

import java.util.Optional;

public class MoodDiaryDao extends AbstractDao<MoodDiary> {
    private final SessionSingleton db = SessionSingleton.getInstance();

    public MoodDiaryDao() {
        super(MoodDiary.class);
    }

    public Optional findByPatient(final String email) {
        return db.executeInTransaction(session -> {
            String queryString = "select p from Person p" +
                    " where lower(p.contactData.email) like" +
                    " lower(:email)";
            Query<Person> query = session.createQuery(queryString, Person.class);
            query.setParameter("email", "%" + email + "%");
            return Optional.ofNullable(query.uniqueResult());
        });
    }
}
