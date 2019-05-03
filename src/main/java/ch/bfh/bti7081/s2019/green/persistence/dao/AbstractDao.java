package ch.bfh.bti7081.s2019.green.persistence.dao;

import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import org.hibernate.Query;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> {
    private final Class<T> clazz;

    public AbstractDao(Class<T> clazz){
        this.clazz = clazz;
    }

    public List<T> findAll(){
        Optional result = SessionSingleton.getInstance().executeInTransaction(session -> {
            Query query = session.createQuery(String.format("select t from %s t", clazz.getSimpleName()));
            return Optional.ofNullable(query.list());
        });

        return (List<T>) result.orElse(Collections.EMPTY_LIST);
    }

    public Optional findById(final Serializable id){
        return SessionSingleton.getInstance().executeInTransaction(session -> {
            return Optional.ofNullable(session.get(clazz, id));
        });
    }

    public void save(T object){
        SessionSingleton.getInstance().executeInTransactionNoResult(session -> {
            session.persist(object);
        });
    }

    public void delete(T object){
        SessionSingleton.getInstance().executeInTransactionNoResult(session -> {
            session.delete(object);
        });
    }
}
