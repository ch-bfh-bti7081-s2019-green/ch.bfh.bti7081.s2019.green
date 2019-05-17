package ch.bfh.bti7081.s2019.green.persistence.dao;

import ch.bfh.bti7081.s2019.green.model.chat.Channel;
import ch.bfh.bti7081.s2019.green.model.chat.Message;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;

public class MessageDao extends AbstractDao<Message>{
    private final SessionSingleton db = SessionSingleton.getInstance();

    public MessageDao() {
        super(Message.class);
    }
}
