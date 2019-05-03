package ch.bfh.bti7081.s2019.green;

import ch.bfh.bti7081.s2019.green.layout.DefaultRouterLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

/**
 * The main view contains a button and a click listener.
 */
@Route(layout = DefaultRouterLayout.class)
@PWA(name = "Patient Management System", shortName = "PMS")
public class MainView extends VerticalLayout {

    public MainView() {
        Button button = new Button("Click me",
                event -> Notification.show("You have clicked the 'Click me'-Button!"));
        add(button);
    }
}
