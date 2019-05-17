package ch.bfh.bti7081.s2019.green.chat;

import ch.bfh.bti7081.s2019.green.model.chat.Channel;
import ch.bfh.bti7081.s2019.green.model.chat.Message;
import ch.bfh.bti7081.s2019.green.model.person.Person;

import java.time.ZonedDateTime;
import java.util.List;

public class ChannelClient {

    private final Person person;
    private final Channel channel;
    private final NotifcationService service;

    /**
     * @param person - the person using this client
     * @param channel - the channel to connect to
     */
    public ChannelClient(Person person, Channel channel, NotifcationService notifcationService){
        this.person = person;
        this.channel = channel;
        this.service = notifcationService;
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
    }

    public Person getPerson() {
        return person;
    }
}
