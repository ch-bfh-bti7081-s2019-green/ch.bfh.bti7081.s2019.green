package ch.bfh.bti7081.s2019.green.presenter;

import ch.bfh.bti7081.s2019.green.LoginUtils;
import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.persistence.dao.PersonDao;
import ch.bfh.bti7081.s2019.green.view.LoginView;

import java.util.Optional;

public class LoginPresenter implements LoginView.LoginButtonListener {

    private LoginView view;
    private PersonDao personDao;

    public LoginPresenter(LoginView view, PersonDao personDao) {

        // Pass the view, model and listeners
        this.view = view;
        this.personDao = personDao;
        this.view.addLoginButtonListener(this);
    }

    @Override
    public void buttonClick(String email, String password) {
        Optional<Person> p = personDao.findByEmail(email);
        if(LoginUtils.validatePassword(
                password, p.get().getPassword())) {
            //If true -> log in and change to main view!
        }
    }
}
