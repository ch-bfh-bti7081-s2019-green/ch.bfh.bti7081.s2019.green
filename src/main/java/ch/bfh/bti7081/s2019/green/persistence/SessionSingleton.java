package ch.bfh.bti7081.s2019.green.persistence;

import ch.bfh.bti7081.s2019.green.model.Contact;
import ch.bfh.bti7081.s2019.green.model.Person;
import ch.bfh.bti7081.s2019.green.persistence.converters.LocalDateConverter;
import ch.bfh.bti7081.s2019.green.persistence.converters.LocalDateTimeConverter;
import ch.bfh.bti7081.s2019.green.persistence.converters.ZonedDateTimeConverter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class SessionSingleton {
    private static SessionSingleton instance = null;
    private final SessionFactory sessionFactory;
    private final Session session;

    private static final Logger LOG = LoggerFactory.getLogger(SessionSingleton.class);

    private SessionSingleton() {
        Configuration config = createConfig();


        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties())
                .configure("hibernate.cfg.xml")
                .build();


        sessionFactory = config.buildSessionFactory(registry);
        session = sessionFactory.openSession();
    }

    private Configuration createConfig() {
        Configuration config = new Configuration();

        // Register converters
        config.addAnnotatedClass(LocalDateConverter.class);
        config.addAnnotatedClass(LocalDateTimeConverter.class);
        config.addAnnotatedClass(ZonedDateTimeConverter.class);

        // Register annotated classes
        config.addAnnotatedClass(Person.class);
        config.addAnnotatedClass(Contact.class);

        return config;
    }

    public static SessionSingleton getInstance() {
        if (instance == null) {
            instance = new SessionSingleton();
        }
        return instance;
    }

    public void executeInTransactionNoResult(Consumer<Session> runnable){
        try {
            executeInTransactionNoResult(runnable, 10);
        } catch (HibernateException hex) {
            LOG.error(hex.getMessage(), hex);
        }
    }

    public void executeInTransactionNoResult(Consumer<Session> runnable, int timeoutInSeconds){
        try {
            executeInTransaction(s -> {
                runnable.accept(s);
                return null;
            }, 10);
        } catch (HibernateException hex) {
            LOG.error(hex.getMessage(), hex);
        }
    }

    public Optional executeInTransaction(Function<Session, Optional> runnable) {
        try {
            return executeInTransaction(runnable, 10);
        } catch (HibernateException hex) {
            LOG.error(hex.getMessage(), hex);
        }
        return Optional.empty();
    }

    public Optional executeInTransaction(Function<Session, Optional> runnable, int timeoutInSeconds) {
        try {
            Transaction transaction = session.getTransaction();
            transaction.setTimeout(timeoutInSeconds);
            transaction.begin();
            Optional result = runnable.apply(session);
            transaction.commit();
            return result;
        } catch (HibernateException hex) {
            LOG.error(hex.getMessage(), hex);
        }
        return Optional.empty();
    }
}
