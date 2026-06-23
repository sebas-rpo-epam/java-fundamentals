package org.epamcampus.solid.dip;

public record AuthResult(boolean success, String message, String token) {

    public static AuthResult success(String token) {
        return new AuthResult(true, "Authentication successful", token);
    }

    public static AuthResult failure(String reason) {
        return new AuthResult(false, reason, null);
    }

    @Override
    public String toString() {
        return success ? "SUCCESS token=" + token : "FAILED  reason=" + message;
    }
}
