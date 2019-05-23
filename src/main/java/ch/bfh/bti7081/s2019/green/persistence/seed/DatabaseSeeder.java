package ch.bfh.bti7081.s2019.green.persistence.seed;

import ch.bfh.bti7081.s2019.green.model.diary.MoodDiary;
import ch.bfh.bti7081.s2019.green.model.person.Patient;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import com.github.javafaker.Faker;
import org.hibernate.boot.Metadata;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


/**
 * This class is used to nuke the current database, rebuild it and seed it
 * with predefined data.
 *
 * This class can be directly executed using maven:
 * <code>mvn compile exec:java@seed</code>
 */
public class DatabaseSeeder {

    private static SessionSingleton db = SessionSingleton.getInstance();
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
        Patient patient = getRandomPatient();
        db.save(patient);

        MoodDiary diary = new MoodDiary();
        patient.setDiary(diary);
        db.save(diary);

    }

    private static Patient getRandomPatient() {
        Patient patient = new Patient();
        patient.setFirstName(faker.name().firstName());
        patient.setLastName(faker.name().lastName());
        patient.setAhvNumber((int) faker.number().randomNumber(13, true));
        patient.setBirthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        patient.setUsername(faker.name().username());

        return patient;
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
