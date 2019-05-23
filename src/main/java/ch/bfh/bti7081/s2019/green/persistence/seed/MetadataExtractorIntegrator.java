package ch.bfh.bti7081.s2019.green.persistence.seed;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.model.relational.Database;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

/**
 * This class is used to get access to the hibernate metadata.
 *
 * This is based on the following article:
 * https://vladmihalcea.com/how-to-get-access-to-database-table-metadata-with-hibernate-5/
 */
public class MetadataExtractorIntegrator implements org.hibernate.integrator.spi.Integrator {


    public static final MetadataExtractorIntegrator INSTANCE =
            new MetadataExtractorIntegrator();

    private Database database;

    private Metadata metadata;

    public Database getDatabase() {
        return database;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    @Override
    public void integrate(
            Metadata metadata,
            SessionFactoryImplementor sessionFactory,
            SessionFactoryServiceRegistry serviceRegistry) {

        this.database = metadata.getDatabase();
        this.metadata = metadata;
    }

    @Override
    public void disintegrate(
            SessionFactoryImplementor sessionFactory,
            SessionFactoryServiceRegistry serviceRegistry) {

    }
}