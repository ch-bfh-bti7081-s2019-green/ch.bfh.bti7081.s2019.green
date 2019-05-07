package ch.bfh.bti7081.s2019.green.persistence.dao;

import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import org.hibernate.Query;

import javax.persistence.TypedQuery;
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
        Optional<List<T>> result = SessionSingleton.getInstance().executeInTransaction(session -> {
            TypedQuery<T> query = session.createQuery(String.format("select t from %s t", clazz.getSimpleName()), clazz);
            return Optional.ofNullable(query.getResultList());
        });

        return result.orElse(Collections.emptyList());
    }

    public Optional<T> findById(final Serializable id){
        return SessionSingleton.getInstance().executeInTransaction(session -> {
            return Optional.ofNullable(session.get(clazz, id));
        });
    }
}
