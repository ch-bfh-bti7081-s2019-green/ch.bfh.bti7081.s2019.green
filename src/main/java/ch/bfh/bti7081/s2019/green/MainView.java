package ch.bfh.bti7081.s2019.green;

import ch.bfh.bti7081.s2019.green.layout.DefaultRouterLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route(value = "", layout = DefaultRouterLayout.class)
public class MainView extends VerticalLayout {

    public MainView() {
        if(!AuthService.isLoggedIn()){
            //getUI().ifPresent(ui -> ui.getPage().executeJavaScript("window.location.href = '/login';location.reload()"));
        }else{
            Button button = new Button("Click me",
                    event -> Notification.show("You have clicked the 'Click me'-Button!"));
            add(button);
        }
    }
}
