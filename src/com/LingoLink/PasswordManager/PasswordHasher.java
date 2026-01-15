
package com.LingoLink.PasswordManager;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author 4-narghirov
 */
public class PasswordHasher {

    // Work factor (10–12 is common for production)
    private static final int SALT_ROUNDS = 12;

    /**
     * Hash a plain-text password
     */
    public static String hashPassword(String plainPassword) {
        if (plainPassword == null || plainPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(SALT_ROUNDS));
    }

    /**
     * Verify a plain-text password against a stored hash
     */
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            return false;
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}