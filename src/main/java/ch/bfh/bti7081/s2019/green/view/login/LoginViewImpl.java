package ch.bfh.bti7081.s2019.green.view.login;

import ch.bfh.bti7081.s2019.green.AuthService;
import ch.bfh.bti7081.s2019.green.layout.DefaultRouterLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route(value = "login")
@PWA(name = "Patient Management System", shortName = "PMS")
public class LoginViewImpl extends VerticalLayout {

    private LoginForm loginForm;

    public LoginViewImpl() {

        System.out.println("## -- login.called");

        // Create the login form
        loginForm = new LoginForm();
        loginForm.addLoginListener(e -> {
            AuthService authService = new AuthService();
                if (authService.login(e.getUsername(), e.getPassword())) {
                    UI.getCurrent().navigate("home");
                }
                else {
                    displayErrorMessage();
                }
        });
        add(loginForm);
    }

    public void displayErrorMessage() {
        loginForm.setError(true);
    }

}
