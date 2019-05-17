package ch.bfh.bti7081.s2019.green.persistence.chat;

import ch.bfh.bti7081.s2019.green.chat.ChannelClient;
import ch.bfh.bti7081.s2019.green.chat.NotifcationService;
import ch.bfh.bti7081.s2019.green.model.chat.Channel;
import ch.bfh.bti7081.s2019.green.model.chat.Message;
import ch.bfh.bti7081.s2019.green.model.person.Patient;
import ch.bfh.bti7081.s2019.green.model.person.Therapist;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;
import ch.bfh.bti7081.s2019.green.persistence.util.DbTestUtil;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ChatTest {
    private static final SessionSingleton db = SessionSingleton.getInstance();
    private Channel channel;
    private Therapist joseph;
    private Patient ruben;

    @Mock
    private NotifcationService service;

    @BeforeClass
    public static void dbReset(){
        DbTestUtil.reset(db, "MESSAGES", "CHANNELS", "PERSON", "PATIENT", "THERAPIST");
    }

    @Before
    public void setup(){
        channel = new Channel();
        channel.setName("DnD-Group");

        ruben = new Patient();
        ruben.setFirstName("Ruben");
        ruben.setLastName("Keinonen");
        ruben.setAhvNumber(1);
        ruben.setBirthDate(LocalDate.of(1927, 7, 30));
        ruben.setUsername("Sulpun");

        db.save(ruben);

        joseph = new Therapist();
        joseph.setFirstName("Joseph");
        joseph.setLastName("Tadena");
        joseph.setAhvNumber(1337);
        joseph.setBirthDate(LocalDate.of(1944, 4, 12));
        joseph.setUsername("xxXLeetHaxorXxx");
        joseph.addPatient(ruben);

        db.save(joseph);

        channel.addMember(joseph);
        channel.addMember(ruben);

        db.save(channel);
    }

    @Test
    public void testChat(){
        ChannelClient clientJoseph = new ChannelClient(joseph, channel, service);
        ChannelClient clientRuben = new ChannelClient(ruben, channel, service);

        channel.addClient(clientJoseph);
        channel.addClient(clientRuben);

        clientJoseph.sendMessage("Meet me for lunch tomorrow?");

        verify(service, times(1)).notify(any(Message.class));
    }
}
