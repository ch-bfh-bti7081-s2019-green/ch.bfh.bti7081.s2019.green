package ch.bfh.bti7081.s2019.green.persistence.dao;

import ch.bfh.bti7081.s2019.green.model.Person;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import org.hibernate.Query;

import java.util.Optional;

public class PersonDao extends AbstractDao<Person>{
    private final SessionSingleton db = SessionSingleton.getInstance();

    public PersonDao() {
        super(Person.class);
    }

    public Optional findByEmail(final String email){
        return db.executeInTransaction(session -> {
            String queryString = "select p from Person p" +
                    " where lower(p.contactData.email) like" +
                    " lower(:email)";
            Query query = session.createQuery(queryString);
            query.setParameter("email", "%" + email + "%");
            return Optional.ofNullable(query.uniqueResult());
        });
    }
}
