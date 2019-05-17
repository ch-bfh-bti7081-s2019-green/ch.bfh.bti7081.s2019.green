package ch.bfh.bti7081.s2019.green.persistence.dao;

import ch.bfh.bti7081.s2019.green.model.chat.Channel;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import org.hibernate.query.Query;

import java.util.Optional;

public class ChannelDao extends AbstractDao<Channel>{
    private final SessionSingleton db = SessionSingleton.getInstance();

    public ChannelDao() {
        super(Channel.class);
    }
}
