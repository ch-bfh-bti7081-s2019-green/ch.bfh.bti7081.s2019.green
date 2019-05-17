package ch.bfh.bti7081.s2019.green;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LoginUtils {

    public static final PasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public static String getEncodedPassword(String password) {
        return encoder.encode(password);
    }

    public static boolean validatePassword(String password, String encodedPassword) {
        return encoder.matches(password, encodedPassword);
    }
}
