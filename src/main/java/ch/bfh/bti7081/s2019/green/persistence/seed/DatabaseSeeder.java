package ch.bfh.bti7081.s2019.green.persistence.seed;

import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class DatabaseSeeder {

    private static SessionSingleton db = SessionSingleton.getInstance();

    public static void main(String[] args) {
        nukeDatabase();
        rebuildDatabase();

        //PersonSeeder.seed();
        //MoodDiarySeeder.seed();
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

        System.out.println("Dropping table");
    }

    /**
     * Rebuilds the database using hibernate.
     */
    private static void rebuildDatabase() {
        SchemaExport schemaExport = new SchemaExport();
        schemaExport.setHaltOnError(true);
        schemaExport.setFormat(true);
        schemaExport.setDelimiter(";");
        schemaExport.setOutputFile("db-schema.sql");
        schemaExport.execute(EnumSet.of(TargetType.DATABASE), SchemaExport.Action.CREATE, MetadataExtractorIntegrator.INSTANCE.getMetadata());
    }

    private static List<String> getTables() {
        return Arrays.asList("mood_diaries", "entries");
    }

    private static String getNukeQuery() {
        StringBuilder sb = new StringBuilder();
        List<String> tables = getTables();
        for (String table : tables) {
            sb.append("DROP TABLE IF EXISTS " + table + " CASCADE;");
        }

        return sb.toString();
    }


}
