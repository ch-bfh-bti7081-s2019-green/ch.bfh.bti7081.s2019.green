package ch.bfh.bti7081.s2019.green.view;

import com.vaadin.flow.component.login.LoginForm;


public class LoginViewImpl implements LoginView {

    private LoginButtonListener loginButtonListener;

    public LoginViewImpl() {

        // Create the login form
        LoginForm loginForm = new LoginForm();
        loginForm.addLoginListener(e ->
            this.loginButtonListener.buttonClick(e.getUsername(), e.getPassword())
        );

    }


    @Override
    public void addLoginButtonListener(LoginButtonListener listener) {
        this.loginButtonListener = listener;
    }

}
