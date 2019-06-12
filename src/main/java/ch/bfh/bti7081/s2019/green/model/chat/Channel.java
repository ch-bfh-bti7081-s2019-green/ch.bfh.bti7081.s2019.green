package ch.bfh.bti7081.s2019.green.model.chat;

import ch.bfh.bti7081.s2019.green.chat.ChannelClient;
import ch.bfh.bti7081.s2019.green.chat.NotificationService;
import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
@ToString(exclude = "messages")
@Entity
@Table(name = "CHANNELS")
public class Channel extends AbstractBaseEntity {
    @Column(name = "NAME")
    private String name;

    @ManyToMany(targetEntity = Person.class)
    @JoinTable(name = "CHANNEL_MEMBERS",
            joinColumns = @JoinColumn(name = "CHANNEL_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID"))
    private List<Person> members;

    @OneToMany(mappedBy = "channel")
    @OrderBy("AUTHOR_TIME")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Message> messages;

    // currently connected clients
    @Transient
    private List<ChannelClient> clients = new ArrayList<>();

    public void addMessage(Message message) {
        if (messages == null) {
            messages = new ArrayList<>();
        }

        message.setChannel(this);
        messages.add(message);
        NotificationService.notify(message);
    }

    public List<Message> getMessages() {
        if (messages == null) {
            messages = new ArrayList<>();
        }
        return messages;
    }

    public void addMember(Person memeber) {
        if (members == null) {
            members = new ArrayList<>();
        }
        members.add(memeber);
    }

    public List<Person> getMembers() {
        if (members == null) {
            members = new ArrayList<>();
        }
        return members;
    }

    public void addClient(ChannelClient client) {
        this.clients.add(client);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return Objects.equals(name, channel.name) &&
                Objects.equals(members, channel.members) &&
                Objects.equals(messages, channel.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, members, messages);
    }
}