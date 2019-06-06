package ch.bfh.bti7081.s2019.green;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route(value = "")
public class MainView extends VerticalLayout {

    public MainView() {
        if(!AuthService.isLoggedIn()){
            System.out.println("## -- navigating to login");
            UI.getCurrent().navigate("login");
        }else{
            System.out.println("## -- already logged in");
            this.add(new Text("You're logged in!"));
        }
    }
}
