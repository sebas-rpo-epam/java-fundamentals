package org.epamcampus.solid.dip;

import java.util.Objects;

// ✅ DIP: high-level module depends ONLY on abstractions (interfaces)
// Dependencies are INJECTED from outside — no new() calls inside
public class AuthService {

    private final UserRepository userRepository;   // ← interface, not impl
    private final PasswordEncoder passwordEncoder; // ← interface, not impl
    private final TokenService tokenService;       // ← interface, not impl

    // Constructor injection: explicit, immutable, testable
    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       TokenService tokenService) {
        this.userRepository  = Objects.requireNonNull(userRepository);
        this.passwordEncoder = Objects.requireNonNull(passwordEncoder);
        this.tokenService    = Objects.requireNonNull(tokenService);
    }

    public AuthResult authenticate(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.passwordHash()))
                .map(user -> AuthResult.success(tokenService.generate(user)))
                .orElse(AuthResult.failure("Invalid credentials"));
    }
}
