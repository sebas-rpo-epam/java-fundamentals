package org.epamcampus.solid.dip;

import java.util.*;

public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> store = new HashMap<>();

    public InMemoryUserRepository(User... seed) {
        for (User u : seed) store.put(u.email(), u);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(store.get(email));
    }

    @Override
    public User save(User user) {
        store.put(user.email(), user);
        return user;
    }
}
