package ch.bfh.bti7081.s2019.green.persistence.dao;

import ch.bfh.bti7081.s2019.green.model.diary.Activity;
import ch.bfh.bti7081.s2019.green.model.diary.ActivityType;
import ch.bfh.bti7081.s2019.green.model.diary.Entry;
import ch.bfh.bti7081.s2019.green.model.diary.MoodDiary;
import ch.bfh.bti7081.s2019.green.model.person.Patient;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import ch.bfh.bti7081.s2019.green.persistence.util.DbTestUtil;
import ch.bfh.bti7081.s2019.green.persistence.util.IdUtil;
import net.bytebuddy.asm.Advice;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class EntryDaoTest {

    private static EntryDao dao = new EntryDao();
    private static SessionSingleton db = SessionSingleton.getInstance();

    @BeforeClass
    public static void setup() {
        DbTestUtil.reset(db, "ENTRIES");

        Entry entry = new Entry();
        entry.setId(IdUtil.next(Entry.class));
        entry.setDate(LocalDate.now());
        entry.setMood(5);
        entry.setDiary(new MoodDiary());
        entry.setNotes("");
        entry.setSleepHours(2);
        entry.setWaterDrunk(2);

        db.executeInTransactionNoResult(s -> s.save(entry));
    }

    @Test
    public void testFindAll() {
        List<Entry> result = dao.findAll();
        assertThat(result, notNullValue());
        assertThat(result.size(), is(1));
    }
}