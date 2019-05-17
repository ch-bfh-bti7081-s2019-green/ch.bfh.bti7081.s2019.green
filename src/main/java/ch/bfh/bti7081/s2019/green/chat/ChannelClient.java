package ch.bfh.bti7081.s2019.green.chat;

import ch.bfh.bti7081.s2019.green.model.chat.Channel;
import ch.bfh.bti7081.s2019.green.model.chat.Message;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import ch.bfh.bti7081.s2019.green.persistence.dao.ChannelDao;
import ch.bfh.bti7081.s2019.green.persistence.dao.PersonDao;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Encapsulated ChannelClient.
 * The controller interacts with this class to send messages and be notified of new ones.
 */
public class ChannelClient {
    private final Person person;
    private final Channel channel;
    private final NotifcationService service;
    private final SessionSingleton db = SessionSingleton.getInstance();
    private final ChannelDao channelDao = new ChannelDao();
    private final PersonDao personDao = new PersonDao();

    /**
     * @param person - the person using this client
     * @param channel - the channel to connect to
     * @param notificationService placeholder for GUI service
     */
    public ChannelClient(Person person, Channel channel, NotifcationService notificationService){
        this.person = person;
        this.channel = channel;
        this.service = notificationService;
    }

    public List<Person> getChannelMembers(){
        return this.channel.getMembers();
    }

    public boolean isMemberOnline(Person member){
        return channel.isConnected(member);
    }

    public void onMessage(Message msg){
        if(msg.getAuthor().equals(person)){
            // we sent this message. ignore it.
            return;
        }
        service.notify(msg);
    }

    public void sendMessage(String content){
        Message msg = new Message();
        msg.setContent(content);
        msg.setAuthor(person);
        msg.setAuthorTime(ZonedDateTime.now());
        channel.addMessage(msg);
        db.save(msg);
        db.save(channel);
    }

    public Person getPerson() {
        return person;
    }
}
