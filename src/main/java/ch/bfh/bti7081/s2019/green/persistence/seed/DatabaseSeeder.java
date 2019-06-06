package ch.bfh.bti7081.s2019.green.persistence.seed;

import ch.bfh.bti7081.s2019.green.AuthService;
import ch.bfh.bti7081.s2019.green.model.diary.Activity;
import ch.bfh.bti7081.s2019.green.model.diary.ActivityType;
import ch.bfh.bti7081.s2019.green.model.diary.Entry;
import ch.bfh.bti7081.s2019.green.model.diary.MoodDiary;
import ch.bfh.bti7081.s2019.green.model.person.Patient;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import com.github.javafaker.Faker;
import org.hibernate.boot.Metadata;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


/**
 * This class is used to nuke the current database, rebuild it and seed it
 * with predefined data.
 * <p>
 * This class can be directly executed using maven:
 * <code>mvn compile exec:java@seed</code>
 */
public class DatabaseSeeder {

    private static SessionSingleton db = SessionSingleton.getInstance();
    private static DatabaseSeederService databaseSeederService = new DatabaseSeederService();
    private static Metadata metadata = MetadataExtractorIntegrator.INSTANCE.getMetadata();
    private static Faker faker = new Faker();

    public static void main(String[] args) {
        nukeDatabase();
        rebuildDatabase();
        seed();
    }

    /**
     * Seed the database with example data.
     */
    private static void seed() {
        Patient patient = databaseSeederService.getRandomPatient();
        patient.setUsername("patient");
        patient.setPassword(AuthService.getEncodedPassword("patient"));
        db.save(patient);

        MoodDiary diary = new MoodDiary();
        patient.setDiary(diary);
        db.save(diary);

        Entry entry = new Entry();
        entry.setDate(LocalDate.now());
        entry.setMood(5);
        entry.setWaterDrunk(1.5);
        entry.setSleepHours(6.5);
        entry.setNotes("This is an example note for an entry.");
        diary.addEntry(entry);
        db.save(entry);

        Activity medicationActivity = new Activity(ActivityType.MEDICATION);
        medicationActivity.setTime(LocalTime.of(8, 0));
        medicationActivity.setText("Paracetamol 5mg");
        entry.addActivity(medicationActivity);
        db.save(medicationActivity);

        Activity foodActivity = new Activity(ActivityType.FOOD);
        foodActivity.setTime(LocalTime.of(12, 15));
        foodActivity.setText("Beef Burger");
        entry.addActivity(foodActivity);
        db.save(foodActivity);

        Activity exerciseActivity = new Activity(ActivityType.EXERCISE);
        exerciseActivity.setTime(LocalTime.of(17, 30));
        exerciseActivity.setText("Ran 1km");
        entry.addActivity(exerciseActivity);
        db.save(exerciseActivity);
    }

    /**
     * Drops all tables in the database. This is currently only compatible
     * with PostgreSQL.
     */
    private static void nukeDatabase() {
        db.executeInTransactionNoResult(session -> {
            session.createNativeQuery(getNukeQuery()).executeUpdate();
            session.clear();
            session.flush();
        });
    }

    /**
     * Rebuilds the database using hibernate.
     */
    private static void rebuildDatabase() {
        SchemaExport schemaExport = new SchemaExport();
        schemaExport.setHaltOnError(true);
        schemaExport.setFormat(true);
        schemaExport.setDelimiter(";");
        schemaExport.execute(EnumSet.of(TargetType.DATABASE), SchemaExport.Action.CREATE, metadata);
    }

    /**
     * Returns a list of all tables registered by in hibernate.
     *
     * @return A list of table names
     */
    private static List<String> getTables() {
        List<String> tables = new ArrayList<>();

        for (PersistentClass persistentClass : metadata.getEntityBindings()) {
            System.out.println(persistentClass.getTable().getName());
            tables.add(persistentClass.getTable().getName());
        }

        // TODO: Get names of join tables using the hibernate metadata
        tables.add("defer_times");
        tables.add("emergency_contact");

        return tables;
    }

    /**
     * Return the SQL query to drop all tables in the database. This is
     * currently only compatible with PostgreSQL.
     *
     * @return A string containing the nuke query
     */
    private static String getNukeQuery() {
        StringBuilder sb = new StringBuilder();
        List<String> tables = getTables();
        for (String table : tables) {
            sb.append("DROP TABLE IF EXISTS ");
            sb.append(table);
            sb.append(" CASCADE;");
        }

        sb.append("DROP SEQUENCE IF EXISTS hibernate_sequence CASCADE;");

        return sb.toString();
    }
}
