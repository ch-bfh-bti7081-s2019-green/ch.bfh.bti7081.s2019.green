package ch.bfh.bti7081.s2019.green;


import ch.bfh.bti7081.s2019.green.layout.DefaultRouterLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route(value = "", layout = DefaultRouterLayout.class)
public class MainView extends VerticalLayout {
    private static final long serialVersionUID = 760635306639919839L;

    public MainView() {
        add(new Label("Home"));
    }

}
