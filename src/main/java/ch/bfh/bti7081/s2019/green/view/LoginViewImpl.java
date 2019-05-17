package ch.bfh.bti7081.s2019.green.view;

import com.vaadin.flow.component.login.LoginForm;
import java.util.ArrayList;
import java.util.List;


public class LoginViewImpl implements LoginView {

    private List<LoginButtonListener> loginButtonListener = new ArrayList<LoginButtonListener>();

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
