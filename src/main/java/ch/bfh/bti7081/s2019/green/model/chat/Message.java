package ch.bfh.bti7081.s2019.green.model.chat;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.OffsetDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "MESSAGES")
public class Message extends AbstractBaseEntity {
    @ManyToOne
    @JoinColumn(name = "CHANNEL_ID")
    private Channel channel;

    @Column(name = "AUTHOR_TIME")
    private OffsetDateTime authorTime;

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "AUTHOR")
    private Person author;

    @Column(name = "CONTENT")
    private String content;
}
