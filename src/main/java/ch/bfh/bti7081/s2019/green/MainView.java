package ch.bfh.bti7081.s2019.green;


import ch.bfh.bti7081.s2019.green.view.LoginView;
import ch.bfh.bti7081.s2019.green.view.diary.MoodDiaryView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route(value = "")
public class MainView extends VerticalLayout implements BeforeEnterObserver {
    private static final long serialVersionUID = 760635306639919839L;

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (AuthService.isLoggedIn()) {
            beforeEnterEvent.forwardTo(MoodDiaryView.class);
        } else {
            beforeEnterEvent.forwardTo(LoginView.class);
        }
    }
}
