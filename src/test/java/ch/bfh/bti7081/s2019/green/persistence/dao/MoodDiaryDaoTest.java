package ch.bfh.bti7081.s2019.green.persistence.dao;

import ch.bfh.bti7081.s2019.green.AuthService;
import ch.bfh.bti7081.s2019.green.model.diary.Activity;
import ch.bfh.bti7081.s2019.green.model.diary.ActivityType;
import ch.bfh.bti7081.s2019.green.model.diary.Entry;
import ch.bfh.bti7081.s2019.green.model.diary.MoodDiary;
import ch.bfh.bti7081.s2019.green.model.person.Patient;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import ch.bfh.bti7081.s2019.green.persistence.util.DbTestUtil;
import ch.bfh.bti7081.s2019.green.persistence.util.IdUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class MoodDiaryDaoTest {

    private static MoodDiaryDao dao = new MoodDiaryDao();
    private static SessionSingleton db = SessionSingleton.getInstance();

    @AfterClass
    public static void cleanup() {
        DbTestUtil.nuke(db);
    }

    @BeforeClass
    public static void setup() {
        String password = AuthService.getEncodedPassword("pass1234");
        Patient james = createPatientWithDiary(
                30,
                "James Gosling",
                "iMadeJava",
                password,
                "033 12 34 56",
                "james@gosling.com",
                "Coffestr Seattle US");
    }

    /*@Test
    public void testFindAll() {
        List<MoodDiary> result = dao.findAll();

        assertThat(result, notNullValue());
        assertThat(result.size(), is(1));
    }*/

    /*@Test
    public void testFindByPatient() {
        PersonDao personDao = new PersonDao();
        Person james = personDao.findById(1).get();
        assertThat(james.getFirstName(), is("James"));

        Optional<MoodDiary> result = dao.findByPatient(james);
        assertThat(result.isPresent(), is(Boolean.TRUE));
        MoodDiary diary = result.get();
        assertThat(diary.getEntries().size(), is(1));

        Entry entry = diary.getEntries().get(0);
        assertThat(entry.getMood(), is(6.0));

        assertThat(entry.getActivities().size(), is(1));
        Activity activity = entry.getActivities().get(0);
        assertThat(activity.getType(), is(ActivityType.FOOD));
    }*/


    private static Patient createPatientWithDiary(int ahv, String name, String username, String password, String phoneNumber, String email, String address) {
        Patient patient = new Patient();
        patient.setAhvNumber(ahv);
        patient.setBirthDate(LocalDate.now());
        patient.setFirstName(name.split(" ")[0]);
        patient.setLastName(name.split(" ")[1]);
        patient.setUsername(username);
        patient.setPassword(password);


        MoodDiary diary = new MoodDiary();
        diary.setId(IdUtil.next(MoodDiary.class));
        patient.setDiary(diary);
        diary.setPatient(patient);

        Entry entry = new Entry();
        entry.setDate(LocalDate.now());
        entry.setId(IdUtil.next(Entry.class));
        entry.setNotes("Lorem ipsum");
        entry.setSleepHours(7.5);
        entry.setWaterDrunk(3.1);
        entry.setMood(6);
        diary.addEntry(entry);

        Activity activity = new Activity();
        activity.setId(IdUtil.next(Activity.class));
        activity.setText("Hamburger");
        activity.setTime(LocalTime.now());
        activity.setType(ActivityType.FOOD);
        entry.addActivity(activity);

        db.executeInTransactionNoResult(s -> s.save(patient));
        db.executeInTransactionNoResult(s -> s.save(diary));
        db.executeInTransactionNoResult(s -> s.save(entry));
        db.executeInTransactionNoResult(s -> s.save(activity));


        return patient;
    }
}
