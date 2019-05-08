package ch.bfh.bti7081.s2019.green.persistence.dao;

import ch.bfh.bti7081.s2019.green.model.*;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import ch.bfh.bti7081.s2019.green.persistence.util.IdUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class MoodDiaryDaoTest {

    private static MoodDiaryDao dao = new MoodDiaryDao();
    private static SessionSingleton db = SessionSingleton.getInstance();

    @BeforeClass
    public static void setup() {
        Patient james = createPatientWithDiary(1337L,
                "James Gosling",
                "iMadeJava",
                "033 12 34 56",
                "james@gosling.com",
                "Coffestr Seattle US");
    }

    @Test
    public void testFindAll() {
        List<MoodDiary> result = dao.findAll();

        assertThat(result, notNullValue());
        assertThat(result.size(), is(1));
    }

    @Test
    public void testFindByPatient() {
        PersonDao personDao = new PersonDao();
        Person james = personDao.findById(1337L).get();
        assertThat(james.getFirstName(), is("James"));

        Optional<MoodDiary> result = dao.findByPatient(james);
        assertThat(result.isPresent(), is(Boolean.TRUE));
        MoodDiary diary = result.get();
        assertThat(diary.getEntries().size(), is(1));

        Entry entry = diary.getEntries().get(0);
        assertThat(entry.getMood(), is(6));

        assertThat(entry.getActivities().size(), is(1));
        Activity activity = entry.getActivities().get(0);
        assertThat(activity.getType(), is(ActivityType.FOOD));
    }


    private static Patient createPatientWithDiary(Long id, String name, String username, String phoneNumber, String email, String address) {
        Patient patient = new Patient();
        patient.setAhvNumber(id);
        patient.setBirthDate(LocalDate.now());
        patient.setFirstName(name.split(" ")[0]);
        patient.setLastName(name.split(" ")[1]);
        patient.setUsername(username);
        db.executeInTransactionNoResult(s -> s.save(patient));

        MoodDiary diary = new MoodDiary();
        diary.setId(IdUtil.next(MoodDiary.class));
        patient.setDiary(diary);
        diary.setPatient(patient);
        db.executeInTransactionNoResult(s -> s.save(diary));

        Entry entry = new Entry();
        entry.setDate(LocalDate.now());
        entry.setId(IdUtil.next(Entry.class));
        entry.setNotes("Lorem ipsum");
        entry.setSleepHours(7.5);
        entry.setWaterDrunk(3.1);
        entry.setMood(6);
        List<Entry> entries = new ArrayList<>();
        entries.add(entry);

        diary.setEntries(entries);
        db.executeInTransactionNoResult(s -> s.save(entry));

        Activity activity = new Activity();
        activity.setId(IdUtil.next(Activity.class));
        activity.setText("Hamburger");
        activity.setTime(LocalTime.now());
        activity.setType(ActivityType.FOOD);

        List<Activity> activities = new ArrayList<>();
        activities.add(activity);

        entry.setActivities(activities);
        db.executeInTransactionNoResult(s -> s.save(activity));

        return patient;
    }
}