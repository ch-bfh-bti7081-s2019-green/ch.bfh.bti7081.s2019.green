package ch.bfh.bti7081.s2019.green.chat;

import ch.bfh.bti7081.s2019.green.model.chat.Channel;
import ch.bfh.bti7081.s2019.green.model.chat.Message;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import ch.bfh.bti7081.s2019.green.persistence.dao.ChannelDao;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Encapsulated ChannelClient.
 * The controller interacts with this class to send messages and be notified of new ones.
 */
public class ChannelClient {
    private final Person user;
    private final Channel channel;
    private final SessionSingleton db = SessionSingleton.getInstance();
    private final ChannelDao channelDao = new ChannelDao();

    /**
     * @param user    the user using this client
     * @param partner the chat partner
     */
    public ChannelClient(Person user, Person partner) {
        this.user = user;
        this.channel = initChannel(user, partner);
    }

    private Channel initChannel(Person user, Person partner) {
        List<Person> m = new ArrayList<>();
        m.add(user);
        m.add(partner);

        // find channel
        Optional<Channel> channelCandidate = channelDao.findByMembers(m);

        // if not exist -> create new
        Channel chnnl = channelCandidate.orElseGet(() -> {
            Channel ch = new Channel();
            ch.setName(m.stream().map(Person::getFirstName).collect(Collectors.joining(", ")));
            ch.setMembers(m);
            return ch;
        });

        // register client
        chnnl.addClient(this);

        // save
        db.save(chnnl);
        return chnnl;
    }

    public List<Message> getLatentMessages() {
        return channel.getMessages();
    }

    public List<Message> getLatentMessages(OffsetDateTime since) {
        return channel.getMessages().stream() //
                .filter(msg -> msg.getAuthorTime().isAfter(since)) //
                .collect(Collectors.toList());
    }


    public void sendMessage(String content) {
        Message msg = new Message();
        msg.setContent(content);
        msg.setAuthor(user);
        msg.setAuthorTime(OffsetDateTime.now());
        db.save(msg);

        channel.addMessage(msg);
        db.save(channel);
    }

    public Person getUser() {
        return user;
    }

    public Channel getChannel() {
        return channel;
    }
}
