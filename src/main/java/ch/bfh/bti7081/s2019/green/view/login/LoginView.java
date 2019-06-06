package ch.bfh.bti7081.s2019.green.view.login;

import ch.bfh.bti7081.s2019.green.AuthService;
import ch.bfh.bti7081.s2019.green.view.diary.MoodDiaryView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route(value = "login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private static final long serialVersionUID = -6910353903305708928L;
    private AuthService authService = new AuthService();
    private LoginForm loginForm;

    public LoginView() {
        setHeightFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        // Create the login form
        loginForm = new LoginForm();
        loginForm.addLoginListener(e -> {
            if (authService.login(e.getUsername(), e.getPassword())) {
                successfulLogin();
            } else {
                displayErrorMessage();
            }
        });
        add(loginForm);

    }

    private void successfulLogin() {
        UI.getCurrent().navigate(MoodDiaryView.class);
    }

    private void displayErrorMessage() {
        loginForm.setError(true);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (AuthService.isLoggedIn()) {
            beforeEnterEvent.forwardTo(MoodDiaryView.class);
        }
    }
}
