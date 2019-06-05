package ch.bfh.bti7081.s2019.green;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main view contains a button and a click listener.
 */
@Route(value = "")
public class MainView extends VerticalLayout {
    Logger logger = LoggerFactory.getLogger(MainView.class);

    public MainView() {
        if (AuthService.isLoggedIn()) {
            UI.getCurrent().navigate("home");
        } else {
            UI.getCurrent().navigate("login");
        }
    }
}
