package ch.bfh.bti7081.s2019.green.persistence.dao;

import ch.bfh.bti7081.s2019.green.model.Person;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class PersonDaoTest {

    private static PersonDao dao = new PersonDao();

    @BeforeClass
    public static void setup(){
        Person james = createPerson(1337L, "James Gosling", "iMadeJava");
        Person richard = createPerson(42L, "Richard Stallman", "freeSoftwareIsCool");

        dao.save(james);
        dao.save(richard);
    }

    @Test
    public void testFindAll(){
        List<Person> result = dao.findAll();

        assertThat(result, notNullValue());
        assertThat(result.size(), is(2));
    }

    @Test
    public void testFindById(){
        Optional<Person> result = dao.findById(42L);

        assertThat(result.isPresent(), is(Boolean.TRUE));
        assertThat(result.get().getFirstname(), is("Richard"));
    }

    private static Person createPerson(Long id, String name, String username){
        Person person = new Person();
        person.setAhvNumber(id);
        person.setBirthdate(LocalDate.now());
        person.setFirstname(name.split(" ")[0]);
        person.setLastname(name.split(" ")[1]);
        person.setUsername(username);

        return person;
    }
}