package ch.bfh.bti7081.s2019.green.view;

public interface LoginView {

    interface LoginButtonListener {
        void buttonClick(String username, String password);
    }

    void addLoginButtonListener(LoginButtonListener listener);

}
