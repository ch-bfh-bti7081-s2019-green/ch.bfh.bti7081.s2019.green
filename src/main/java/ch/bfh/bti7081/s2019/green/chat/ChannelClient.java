package ch.bfh.bti7081.s2019.green.chat;

import ch.bfh.bti7081.s2019.green.model.chat.Channel;
import ch.bfh.bti7081.s2019.green.model.chat.Message;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import ch.bfh.bti7081.s2019.green.persistence.dao.ChannelDao;
import ch.bfh.bti7081.s2019.green.view.chat.ChatView;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
    private final ChatView view;
    private final SessionSingleton db = SessionSingleton.getInstance();
    private final ChannelDao channelDao = new ChannelDao();

    /**
     * @param chatView the view for displaying the chat
     * @param user the user using this client
     * @param members the other members of this channel
     */
    public ChannelClient(ChatView chatView, Person user, Person... members) {
        this.view = chatView;
        this.user = user;
        this.channel = initChannel(user, members);
    }

    private Channel initChannel(Person user, Person... members){
        List<Person> m = new ArrayList<>();
        m.add(user);
        m.addAll(Arrays.asList(members));

        // find channel
        Optional<Channel> channelCandidate = channelDao.findByMembers(Arrays.asList(members));

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

    public List<Person> getChannelMembers() {
        return this.channel.getMembers();
    }

    public boolean isMemberOnline(Person member) {
        return channel.isConnected(member);
    }

    public void onMessage(Message msg) {
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
        msg.setAuthor(user);
        msg.setAuthorTime(ZonedDateTime.now());
        channel.addMessage(msg);
        db.save(msg);
        db.save(channel);
    }

    public Person getUser() {
        return user;
    }
}
