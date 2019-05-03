package ch.bfh.bti7081.s2019.green.persistence.dao;

import ch.bfh.bti7081.s2019.green.model.Person;

public class PersonDao extends AbstractDao<Person>{
    public PersonDao() {
        super(Person.class);
    }
}
