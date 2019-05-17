package ch.bfh.bti7081.s2019.green.model.chat;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "MESSAGES")
public class Message extends AbstractBaseEntity {
    @ManyToOne
    @Column(name = "CHANNEL_ID")
    private Channel channel;

    @Column(name = "AUTHOR_TIME")
    private ZonedDateTime authorTime;

    @Column(name = "AUTHOR")
    private Person author;

    @Column(name = "CONTENT")
    private String content;
}
