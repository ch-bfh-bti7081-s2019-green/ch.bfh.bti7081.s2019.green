package ch.bfh.bti7081.s2019.green.persistence.dao;

import ch.bfh.bti7081.s2019.green.model.chat.Channel;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class ChannelDao extends AbstractDao<Channel> {
    private final SessionSingleton db = SessionSingleton.getInstance();

    public ChannelDao() {
        super(Channel.class);
    }

    public Optional<Channel> findByMembers(List<Person> members) {
        StringBuilder sql = new StringBuilder();
        sql.append("select ch from Channel ch") //
                .append(" where 1=1"); //
        for (int i = 0; i < members.size(); i++) {
            sql.append(" and ?") //
                    .append(i) //
                    .append(" member of ch.members");
        }

        return db.executeInTransaction(session -> {
            Query<Channel> query = session.createQuery(sql.toString(), Channel.class);
            for (int i = 0; i < members.size(); i++) {
                query.setParameter(i, members.get(i));
            }
            return Optional.ofNullable(query.uniqueResult());
        });
    }
}