package ch.bfh.bti7081.s2019.green.view;

public interface LoginView {

    interface LoginButtonListener {
        void buttonClick(String email, String password);
    }

    void addLoginButtonListener(LoginButtonListener listener);

}
