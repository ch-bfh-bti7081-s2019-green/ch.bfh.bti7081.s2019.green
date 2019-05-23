package ch.bfh.bti7081.s2019.green.persistence.seed;

import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import org.hibernate.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import java.util.Arrays;
import java.util.List;

public class DatabaseSeeder {

    private static SessionSingleton db = SessionSingleton.getInstance();

    public static void main(String[] args) {
        nukeDatabase();

        PersonSeeder.seed();
        MoodDiarySeeder.seed();
    }

    private static void nukeDatabase() {

        db.executeInTransactionNoResult(session -> {
            session.createNativeQuery(getNukeQuery()).executeUpdate();
            session.clear();
            session.flush();
        });

        System.out.println("Dropping table");
    }

    private static List<String> getTables() {
        return Arrays.asList("mood_diaries", "entries");
    }

    private static String getNukeQuery() {
        StringBuilder sb = new StringBuilder();
        List<String> tables = getTables();
        for(String table : tables) {
            sb.append("DROP TABLE IF EXISTS " + table + " CASCADE;");
        }

        return sb.toString();
    }


}
