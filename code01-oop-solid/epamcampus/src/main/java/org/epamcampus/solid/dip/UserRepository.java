package org.epamcampus.solid.dip;

import java.util.Optional;

// Abstraction in the DOMAIN layer — both high and low levels depend on this
public interface UserRepository {
    Optional<User> findByEmail(String email);
    User save(User user);
}
