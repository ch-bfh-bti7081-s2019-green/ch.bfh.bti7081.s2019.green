package ch.bfh.bti7081.s2019.green.presenter;

import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.view.LoginView;

public class LoginPresenter implements LoginView.LoginButtonListener {

    private LoginView view;
    private Person person;

    public LoginPresenter(LoginView view, Person person) {

        // Pass the view, model and listeners
        this.view = view;
        this.person = person;
        this.view.addLoginButtonListener(this);
    }

    @Override
    public void buttonClick(String username, String password) {

    }
}
