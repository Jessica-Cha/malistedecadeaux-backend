package fr.jc.security;

import org.mindrot.jbcrypt.BCrypt;
public class PasswordHasher {
    public static String hashPassword(String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashedPassword;
    }

    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
