package ch.bfh.bti7081.s2019.green.persistence.seed;

import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import org.hibernate.boot.Metadata;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

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

    public static void main(String[] args) {
        nukeDatabase();
        rebuildDatabase();

        // TODO: Add seed code here
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

        return sb.toString();
    }
}
