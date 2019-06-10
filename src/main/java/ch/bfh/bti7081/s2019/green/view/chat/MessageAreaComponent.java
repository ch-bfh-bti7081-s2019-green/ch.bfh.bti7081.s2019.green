package ch.bfh.bti7081.s2019.green.view.chat;

import ch.bfh.bti7081.s2019.green.chat.NotificationService;
import ch.bfh.bti7081.s2019.green.model.chat.Channel;
import ch.bfh.bti7081.s2019.green.model.chat.Message;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class MessageAreaComponent extends VerticalLayout {
    private transient Registration notificationRegistration;
    private final transient Person user;
    private final transient Channel channel;

    public MessageAreaComponent(Person user, Channel channel) {
        this.user = user;
        this.channel = channel;
        this.setWidthFull();
        this.setHeightFull();
        this.setAlignItems(Alignment.END);
        this.getStyle().set("overflow", "scroll");
    }

    @Override
    protected void onAttach(final AttachEvent attachEvent) {
        // register for NotificationService
        notificationRegistration = NotificationService.register(channel, msg -> {
            System.err.printf("Updating for @%s with \"%s\"%n", user.getFullName(), msg.getContent());
            UI ui = attachEvent.getUI();
            ui.access(() -> {
                this.add(createMessageBubble(msg));
                ui.push();
            });
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        // unregister from NotificationService
        notificationRegistration.remove();
        notificationRegistration = null;
    }

    public void addLatentMessages(List<Message> latentMessages) {
        latentMessages.stream()
                .map(this::createMessageBubble)
                .forEach(this::add);
    }

    private HorizontalLayout createMessageBubble(final Message msg) {
        final HorizontalLayout container = new HorizontalLayout();
        container.setWidthFull();

        if (msg.getAuthor().equals(user)) {
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
}
