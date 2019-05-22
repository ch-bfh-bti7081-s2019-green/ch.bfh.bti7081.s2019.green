package ch.bfh.bti7081.s2019.green.view;

import ch.bfh.bti7081.s2019.green.layout.DefaultRouterLayout;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route(value = "login", layout = DefaultRouterLayout.class)
@PWA(name = "Patient Management System", shortName = "PMS")
public class LoginViewImpl extends VerticalLayout implements LoginView {

    private LoginButtonListener loginButtonListener;

    public LoginViewImpl() {

        // Create the login form
        LoginForm loginForm = new LoginForm();
        loginForm.addLoginListener(e ->
            this.loginButtonListener.buttonClick(e.getUsername(), e.getPassword())
        );
        add(loginForm);

    }


    @Override
    public void addLoginButtonListener(LoginButtonListener listener) {
        this.loginButtonListener = listener;
    }

}
