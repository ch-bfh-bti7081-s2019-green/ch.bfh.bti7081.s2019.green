package ch.bfh.bti7081.s2019.green.presenter;

import ch.bfh.bti7081.s2019.green.AuthService;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.persistence.dao.PersonDao;
import ch.bfh.bti7081.s2019.green.view.LoginView;

import java.util.Optional;

public class LoginPresenter implements LoginView.LoginButtonListener {

    private LoginView view;
    private AuthService authService;

    public LoginPresenter(LoginView view, AuthService authService) {

        // Pass the view, model and listeners
        this.view = view;
        this.authService = authService;
        this.view.addLoginButtonListener(this);
    }

    @Override
    public void buttonClick(String email, String password) {


    }
}
