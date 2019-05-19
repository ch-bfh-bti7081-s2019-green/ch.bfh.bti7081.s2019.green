package ch.bfh.bti7081.s2019.green.persistence.util;

import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbTestUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbTestUtil.class);

    /**
     * Nukes the database and creates a new one from scratch using the test_schema.sql file.
     */
    public static void nuke(final SessionSingleton db) {
        db.executeInTransactionNoResult(session -> {
            session.createNativeQuery("drop all objects delete files;RUNSCRIPT FROM './src/test/resources/test_schema.sql'").executeUpdate();
            session.clear();
            session.flush();
        });
    }
}
