package org.epamcampus.solid.dip;

public class MainDip {

    public static void run() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║       SOLID — D: Dependency Inversion    ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // ─── BAD: high-level creates its own low-level dependencies ────────
        System.out.println("\n--- BAD: BadAuthService (hardcoded dependencies) ---");
        System.out.println("  Problem: to test this you need the real 'database' baked in.");
        System.out.println("  Problem: swapping BCrypt for Argon2 requires editing AuthService.");
        BadAuthService bad = new BadAuthService();
        System.out.println("  Auth alice/secret123: " + bad.authenticate("alice@epam.com", "secret123"));
        System.out.println("  Auth wrong password:  " + bad.authenticate("alice@epam.com", "wrong"));
        System.out.println("  Auth unknown user:    " + bad.authenticate("nobody@epam.com", "x"));

        // ─── GOOD: dependencies injected — both layers depend on abstractions
        System.out.println("\n--- GOOD: AuthService (constructor injection) ---");

        PlainPasswordEncoder encoder = new PlainPasswordEncoder();
        User alice = new User("u1", "alice@epam.com", encoder.encode("secret123"), "ADMIN");

        // Wiring: the caller (IoC container in Spring, or us manually here) provides all deps
        AuthService authService = new AuthService(
                new InMemoryUserRepository(alice),  // could be JpaUserRepository in prod
                encoder,                             // could be BCryptPasswordEncoder in prod
                new SimpleTokenService()             // could be JwtTokenService in prod
        );

        System.out.println("  Auth alice/secret123: " + authService.authenticate("alice@epam.com", "secret123"));
        System.out.println("  Auth wrong password:  " + authService.authenticate("alice@epam.com", "wrong"));
        System.out.println("  Auth unknown user:    " + authService.authenticate("nobody@epam.com", "x"));

        System.out.println("\nKey insight: to test AuthService, pass an InMemoryUserRepository.");
        System.out.println("  To go to prod, pass JpaUserRepository. AuthService code NEVER changes.");
    }
}
