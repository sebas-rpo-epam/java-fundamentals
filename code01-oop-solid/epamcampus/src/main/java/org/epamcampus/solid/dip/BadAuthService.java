package org.epamcampus.solid.dip;

import java.util.HashMap;
import java.util.Map;

/**
 * ❌ VIOLATES DIP — high-level module (auth logic) depends on low-level details.
 *
 * Problems:
 *  1. Cannot test without a "database" (hardcoded HashMap as stand-in for MySQL)
 *  2. Cannot swap password algorithm without editing AuthService
 *  3. Cannot swap token format without editing AuthService
 *  4. All dependencies created internally — impossible to mock
 */
public class BadAuthService {

    // ← Low-level details created directly inside the high-level class
    private final Map<String, User> database = new HashMap<>();  // simulates MySQL
    private static final String SECRET = "hardcoded-secret";     // simulates BCrypt config

    public BadAuthService() {
        // Hardcoded "database" seed
        database.put("alice@epam.com", new User("u1", "alice@epam.com", "plain:secret123", "ADMIN"));
    }

    public AuthResult authenticate(String email, String password) {
        User user = database.get(email);  // ← coupled to internal HashMap
        if (user == null) return AuthResult.failure("User not found");

        // ← Password check logic embedded — cannot swap algorithm
        if (!user.passwordHash().equals("plain:" + password)) {
            return AuthResult.failure("Invalid password");
        }

        // ← Token generation embedded — cannot swap strategy
        String token = SECRET + "-" + user.id() + "-" + System.currentTimeMillis();
        return AuthResult.success(token);
    }
}
