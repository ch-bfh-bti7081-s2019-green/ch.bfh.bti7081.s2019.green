package ch.bfh.bti7081.s2019.green.persistence.util;

import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbTestUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbTestUtil.class);

    /**
     * Takes a SessionSingleton instance and a list of tables to reset.
     * Uses {@code TRUNCATE TABLE "table_name";}.
     *
     * @param db     the SessionSingleton used in your tests
     * @param tables the list of tables you want to truncate
     */
    public static void reset(final SessionSingleton db, final String... tables) {
        for (String table : tables) {
            if (table.split("\\w+").length == 0) {
                final String template = "TRUNCATE TABLE %s";
                final String sql = String.format(template, table);
                db.executeInTransactionNoResult(session -> {
                    NativeQuery query = session.createNativeQuery(sql);
                    query.executeUpdate();
                });
            } else {
                LOGGER.warn("{} doesn't look like a table name for the regext /\\w+/", table);
            }
        }
    }
}
