package ch.bfh.bti7081.s2019.green.chat;

import ch.bfh.bti7081.s2019.green.model.chat.Channel;
import ch.bfh.bti7081.s2019.green.model.chat.Message;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import ch.bfh.bti7081.s2019.green.view.diary.ChatView;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Encapsulated ChannelClient.
 * The controller interacts with this class to send messages and be notified of new ones.
 */
public class ChannelClient {
    private final Person person;
    private final Channel channel;
    private final ChatView view;
    private final SessionSingleton db = SessionSingleton.getInstance();

    /**
     * @param person  - the person using this client
     * @param channel - the channel to connect to
     */
    public ChannelClient(Person person, Channel channel, ChatView chatView) {
        this.person = person;
        this.channel = channel;
        this.view = chatView;
    }

    public List<Person> getChannelMembers() {
        return this.channel.getMembers();
    }

    public boolean isMemberOnline(Person member) {
        return channel.isConnected(member);
    }

    public void onMessage(Message msg) {
        if (msg.getAuthor().equals(person)) {
            // we sent this message. ignore it.
            return;
        }
        view.notify(msg);
    }

    public List<Message> getLatentMessages() {
        return channel.getMessages();
    }

    public List<Message> getLatentMessages(ZonedDateTime since) {
        return channel.getMessages().stream() //
                .filter(msg -> msg.getAuthorTime().isAfter(since)) //
                .collect(Collectors.toList());
    }


    public void sendMessage(String content) {
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
