package ch.bfh.bti7081.s2019.green.view.chat;

import ch.bfh.bti7081.s2019.green.AuthService;
import ch.bfh.bti7081.s2019.green.chat.ChannelClient;
import ch.bfh.bti7081.s2019.green.layout.DefaultRouterLayout;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.persistence.dao.PersonDao;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

@Route(value = "chat", layout = DefaultRouterLayout.class)
@PageTitle("Chat")
public class ChatView extends VerticalLayout implements AfterNavigationObserver, HasUrlParameter<String> {
    private static final long serialVersionUID = -6432944384499835839L;
    private transient ChannelClient client;
    private transient Person chatPartner;
    private transient PersonDao personDao = new PersonDao();

    public ChatView() {
        this.setHeightFull();
        this.setWidthFull();
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        Person user = AuthService.getCurrentUser();

        client = new ChannelClient(user, chatPartner);

        initializeLayout();
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String userName) {
        personDao.findByUsername(userName).ifPresent(person -> this.chatPartner = person);
    }

    private void initializeLayout() {
        this.addComponentAsFirst(new H2(chatPartner.getFullName()));

        createMessageAreaSublayout();
        createMessageCompositionSublayout();
    }

    private void createMessageAreaSublayout() {
        MessageAreaComponent messageArea = new MessageAreaComponent(client.getUser(), client.getChannel());

        messageArea.addLatentMessages(client.getLatentMessages());

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
        textField.addValueChangeListener(event -> sendButton.setEnabled(!event.getValue().isEmpty()));

        // build message composition layout
        messageField.setWidthFull();
        messageField.add(textField);
        messageField.add(sendButton);

        this.add(messageField);
    }
}