package org.epamcampus.solid.dip;

// Simulates a password encoder — in production this would be BCrypt
public class PlainPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(String rawPassword) {
        return "plain:" + rawPassword;  // NOT secure — demo only!
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return encodedPassword.equals("plain:" + rawPassword);
    }
}
