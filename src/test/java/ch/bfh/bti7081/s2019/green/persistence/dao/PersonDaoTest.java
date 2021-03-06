package ch.bfh.bti7081.s2019.green.persistence.dao;

import ch.bfh.bti7081.s2019.green.AuthService;
import ch.bfh.bti7081.s2019.green.model.person.Contact;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import ch.bfh.bti7081.s2019.green.persistence.util.DbTestUtil;
import ch.bfh.bti7081.s2019.green.persistence.util.IdUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class PersonDaoTest {

    private static PersonDao dao = new PersonDao();
    private static SessionSingleton db = SessionSingleton.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonDaoTest.class);

    @AfterClass
    public static void cleanup() {
        DbTestUtil.nuke(db);
    }

    @BeforeClass
    public static void setup() {
        String password = AuthService.getEncodedPassword("pass123");

        Person james = savePerson(1337,
                "James Gosling",
                "iMadeJava",
                password,
                "033 12 34 56",
                "james@gosling.com",
                "Coffestr Seattle US");


        Person richard = savePerson(42,
                "Richard Stallman",
                "freeSoftwareIsCool",
                password,
                "[redacted]",
                "richard@stallman.org",
                "PlymouthRd Wellington NZ");
    }

    /*@Test
    public void testFindAll() {
        List<Person> result = dao.findAll();

        assertThat(result, notNullValue());
        assertThat(result.size(), is(2));
    }

    @Test
    public void testFindByEmail() {
        Optional<Person> result = dao.findByEmail("riCHard@stallman.org");

        assertThat(result.isPresent(), is(Boolean.TRUE));
        assertThat(result.get().getFirstName(), is("Richard"));
    }*/

    private static Person savePerson(Integer ahvNumber, String name, String username, String password, String phoneNumber, String email, String address) {
        Person person = new Person();

        person.setAhvNumber(ahvNumber);
        person.setBirthDate(LocalDate.now());
        person.setFirstName(name.split(" ")[0]);
        person.setLastName(name.split(" ")[1]);
        person.setUsername(username);
        person.setPassword(password);

        Contact contact = new Contact();
        contact.setId(IdUtil.next(Contact.class)); // Contact doesn't have a natural ID and h2 doesn't have sequences
        contact.setPhone(phoneNumber);
        contact.setEmail(email);
        contact.setStreet(address.split(" ")[0]);
        contact.setCity(address.split(" ")[1]);
        contact.setCountry(address.split(" ")[2]);
        contact.setPerson(person);

        db.executeInTransactionNoResult(s -> s.save(person));
        person.setContactData(contact);
        db.executeInTransactionNoResult(s -> s.save(contact));


        return person;
    }
}
