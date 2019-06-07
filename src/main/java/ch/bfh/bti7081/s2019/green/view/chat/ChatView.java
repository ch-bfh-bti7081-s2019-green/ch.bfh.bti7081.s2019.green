package ch.bfh.bti7081.s2019.green.view.chat;

import ch.bfh.bti7081.s2019.green.AuthService;
import ch.bfh.bti7081.s2019.green.chat.ChannelClient;
import ch.bfh.bti7081.s2019.green.layout.DefaultRouterLayout;
import ch.bfh.bti7081.s2019.green.model.chat.Message;
import ch.bfh.bti7081.s2019.green.model.person.Patient;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.model.person.Therapist;
import ch.bfh.bti7081.s2019.green.persistence.dao.PersonDao;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

@Route(value = "chat", layout = DefaultRouterLayout.class)
@PageTitle("Chat")
public class ChatView extends VerticalLayout implements AfterNavigationObserver, HasUrlParameter<String> {
    private static final long serialVersionUID = -6432944384499835839L;
    private transient ChannelClient client;
    private transient VerticalLayout messageArea;
    private transient Person chatPartner;
    private transient PersonDao personDao = new PersonDao();

    private void initializeLayout() {
        this.setHeightFull();
        this.setWidthFull();
        this.add(new H2(chatPartner.getFullName()));
        createMessageAreaSublayout();
        createMessageCompositionSublayout();

        client.getLatentMessages().stream() //
                .map(this::createMessageBubble) //
                .forEach(layout -> messageArea.add(layout));
    }

    public void notifyUser(Message msg) {
        messageArea.add(createMessageBubble(msg));
    }

    private HorizontalLayout createMessageBubble(Message msg) {
        final HorizontalLayout container = new HorizontalLayout();
        container.setWidthFull();

        if (msg.getAuthor().equals(client.getUser())) {
            // right align
            container.setJustifyContentMode(JustifyContentMode.END);
        } else {
            // left align
            container.setJustifyContentMode(JustifyContentMode.START);
        }

        final TextArea messageText = new TextArea(msg.getAuthor().getFullName());
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
            client.sendMessage(textField.getValue());
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

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        Person user = AuthService.getCurrentUser();

        client = new ChannelClient(this, user, chatPartner);

        initializeLayout();
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String userName) {
        personDao.findByUsername(userName).ifPresent(person -> this.chatPartner = person);
    }
}