package ch.bfh.bti7081.s2019.green;

import ch.bfh.bti7081.s2019.green.model.person.Person;
import ch.bfh.bti7081.s2019.green.persistence.dao.PersonDao;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class AuthService {

    private final PersonDao personDao;
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private static final String USER_SESSION = "USER_SESSION";

    public AuthService() {
        this.personDao = new PersonDao();
    }

    private boolean validatePassword(String password, String encodedPassword) {
        return encoder.matches(password, encodedPassword);
    }

    public static boolean isLoggedIn() {
        return VaadinSession.getCurrent().getAttribute(USER_SESSION) != null;
    }

    public boolean login(String username, String password) {
        Optional<Person> user = personDao.findByUsername(username);

        if (!user.isPresent()) {
            return false;
        }

        if (validatePassword(password, user.get().getPassword())) {
            VaadinSession.getCurrent().setAttribute(USER_SESSION, user.get());
            return true;
        }

        return false;
    }

    public boolean logout() {
        VaadinSession.getCurrent().close();
        if (!AuthService.isLoggedIn()) {
            return true;
        }
        return false;
    }

    //For testing purposes
    public static String getEncodedPassword(String password) {
        return encoder.encode(password);
    }


}
