package ch.bfh.bti7081.s2019.green.persistence.chat;

import ch.bfh.bti7081.s2019.green.chat.ChannelClient;
import ch.bfh.bti7081.s2019.green.chat.NotifcationService;
import ch.bfh.bti7081.s2019.green.model.chat.Channel;
import ch.bfh.bti7081.s2019.green.model.chat.Message;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ChatTest {
    private Channel channel;
    private Person joseph;
    private Person ruben;

    @Mock
    private NotifcationService service;

    @Before
    public void setup(){
        channel = new Channel();
        channel.setName("DnD-Group");

        joseph = new Person();
        joseph.setFirstName("Joseph");
        joseph.setLastName("Tadena");
        joseph.setAhvNumber(1337);
        joseph.setBirthDate(LocalDate.of(1944, 4, 12));
        joseph.setUsername("xxXLeetHaxorXxx");

        ruben = new Person();
        ruben.setFirstName("Ruben");
        ruben.setLastName("Keinonen");
        ruben.setAhvNumber(1);
        ruben.setBirthDate(LocalDate.of(1927, 7, 30));
        ruben.setUsername("Sulpun");

        channel.setMembers(Arrays.asList(joseph, ruben));
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
