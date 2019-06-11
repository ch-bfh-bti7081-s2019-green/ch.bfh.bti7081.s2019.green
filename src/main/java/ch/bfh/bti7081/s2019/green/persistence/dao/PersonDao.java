package ch.bfh.bti7081.s2019.green.persistence.dao;

import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import org.hibernate.query.Query;

import java.util.Optional;

public class PersonDao extends AbstractDao<Person> {
    private final SessionSingleton db = SessionSingleton.getInstance();

    public PersonDao() {
        super(Person.class);
    }

    public Optional<Person> findByUsername(final String username) {
        return db.executeInTransaction(session -> {
            String queryString = "select p from Person p" +
                    " where lower(p.username) = " +
                    " lower(:username)";
            Query<Person> query = session.createQuery(queryString, Person.class);
            query.setParameter("username", username);
            return Optional.ofNullable(query.uniqueResult());
        });
    }


}
