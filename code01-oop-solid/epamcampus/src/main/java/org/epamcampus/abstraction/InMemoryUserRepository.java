package org.epamcampus.abstraction;

import java.util.*;

public class InMemoryUserRepository implements UserRepository {

    private final Map<UUID, User> store = new HashMap<>();

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<User> findByDepartment(String department) {
        return store.values().stream()
                .filter(u -> u.department().equalsIgnoreCase(department))
                .toList();
    }

    @Override
    public User save(User user) {
        store.put(user.id(), user);
        return user;
    }

    @Override
    public void delete(UUID id) {
        store.remove(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return store.values().stream().anyMatch(u -> u.email().equalsIgnoreCase(email));
    }
}
