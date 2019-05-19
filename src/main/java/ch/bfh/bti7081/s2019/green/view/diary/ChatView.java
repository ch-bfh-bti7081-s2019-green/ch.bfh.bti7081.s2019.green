package ch.bfh.bti7081.s2019.green.view.diary;

import ch.bfh.bti7081.s2019.green.chat.ChannelClient;
import ch.bfh.bti7081.s2019.green.layout.DefaultRouterLayout;
import ch.bfh.bti7081.s2019.green.model.chat.Message;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.persistence.dao.PersonDao;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Route(value = "chat", layout = DefaultRouterLayout.class)
@PageTitle("Chat")
public class ChatView extends VerticalLayout {
    private PersonDao personDao = new PersonDao();
    private ChannelClient client;
    private List<Message> allMessages = new ArrayList<>();
    private VerticalLayout messageArea;

    // Entering and sending the message

    Person me = new Person();
    Person them = new Person();

    {
        me.setFirstName("John");
        them.setFirstName("Adam");

        Message msg = new Message();
        msg.setAuthorTime(ZonedDateTime.now().minusMinutes(2));
        msg.setAuthor(me);
        msg.setContent("Hello");

        Message msg2 = new Message();
        msg2.setAuthorTime(ZonedDateTime.now().minusMinutes(1));
        msg2.setAuthor(them);
        msg2.setContent("Hi there");

        Message msg3 = new Message();
        msg3.setAuthorTime(ZonedDateTime.now());
        msg3.setAuthor(me);
        msg3.setContent("I'm texting again now in this conversation.\nThis is multiline.");

        allMessages.add(msg);
        allMessages.add(msg2);
        allMessages.add(msg3);
    }

    public ChatView() {
        //TODO currently logged in person, selected channel
        //this.client = new ChannelClient(null, null, this);
        this.setHeightFull();
        this.setWidthFull();
        createMessageAreaSublayout();
        createMessageCompositionSublayout();

        // work through missed messages
        allMessages.forEach(msg -> notify(msg));
    }

    public void notify(Message msg){
        messageArea.add(createMessageBubbleSubLayout(msg));
    }

    private HorizontalLayout createMessageBubbleSubLayout(Message msg) {
        final HorizontalLayout container = new HorizontalLayout();
        container.setWidthFull();

        if(msg.getAuthor().equals(me)){
            // right align
            container.setJustifyContentMode(JustifyContentMode.END);
        }else {
            // left align
            container.setJustifyContentMode(JustifyContentMode.START);
        }

        final TextArea messageText = new TextArea(msg.getAuthor().getFirstName());
        messageText.setValue(msg.getContent());
        messageText.setMaxWidth("90%");
        messageText.setMinWidth("30%");
        messageText.setReadOnly(true);
        container.add(messageText);

        return container;
    }

    private void createMessageAreaSublayout() {
        messageArea = new VerticalLayout();
        messageArea.setWidthFull();
        messageArea.setHeightFull();
        messageArea.setAlignItems(Alignment.END);
        this.add(messageArea);
    }

    private void createMessageCompositionSublayout() {
        final HorizontalLayout messageField = new HorizontalLayout();
        final Button sendButton = new Button();
        final TextField textField = new TextField();

        // create send button
        sendButton.setWidth("10%");
        sendButton.setIcon(VaadinIcon.PAPERPLANE.create());
        sendButton.addClickListener(event -> {
            //TODO send message
            System.out.printf("Message=%s%n", textField.getValue());
            textField.clear();
        });
        sendButton.setEnabled(false); // will be enabled once textField has content

        // create textfield for entering message
        textField.setAutofocus(true);
        textField.setWidth("90%");
        textField.setMaxLength(950);
        textField.addValueChangeListener(event -> {
            sendButton.setEnabled(!event.getValue().isEmpty());
        });

        // build message composition layout
        messageField.setWidthFull();
        messageField.add(textField);
        messageField.add(sendButton);

        this.add(messageField);
    }
}
