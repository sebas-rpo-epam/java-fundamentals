package org.epamcampus.abstraction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Interface = contract of BEHAVIOUR (CAN-DO), not identity (IS-A)
// Multiple unrelated classes can implement this without sharing a common ancestor
public interface UserRepository {

    Optional<User> findById(UUID id);
    List<User> findByDepartment(String department);
    User save(User user);
    void delete(UUID id);
    boolean existsByEmail(String email);

    // default method: derived convenience behaviour — no need to implement in every class
    default User findByIdOrThrow(UUID id) {
        return findById(id).orElseThrow(
                () -> new RuntimeException("User not found: " + id));
    }
}
