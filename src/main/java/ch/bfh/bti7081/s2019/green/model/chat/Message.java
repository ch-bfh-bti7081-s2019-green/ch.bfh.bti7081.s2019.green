package ch.bfh.bti7081.s2019.green.model.chat;

import ch.bfh.bti7081.s2019.green.model.AbstractBaseEntity;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.persistence.converters.ZonedDateTimeConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "MESSAGES")
public class Message extends AbstractBaseEntity {
    @ManyToOne
    @JoinColumn(name = "CHANNEL_ID")
    private Channel channel;

    @Column(name = "AUTHOR_TIME")
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime authorTime;

    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "AUTHOR")
    private Person author;

    @Column(name = "CONTENT")
    private String content;
}
