package ch.bfh.bti7081.s2019.green.view;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import java.util.ArrayList;
import java.util.List;


public class LoginViewImpl implements LoginView {

    private List<LoginButtonListener> loginButtonListener = new ArrayList<LoginButtonListener>();
    private TextField usernameField;
    private PasswordField passwordField;


    public LoginViewImpl() {

        // Create the login form
        LoginForm loginForm = new LoginForm();
        loginForm.addLoginListener(e -> {
            for (LoginButtonListener listener : loginButtonListener) {
                listener.buttonClick(e.getUsername(), e.getPassword());
            }
        });

    }


    @Override
    public void addLoginButtonListener(LoginButtonListener listener) {
        this.loginButtonListener.add(listener);
    }

}
