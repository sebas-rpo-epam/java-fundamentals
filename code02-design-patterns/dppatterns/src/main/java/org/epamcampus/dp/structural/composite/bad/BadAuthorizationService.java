package org.epamcampus.dp.structural.composite.bad;

import org.epamcampus.dp.structural.composite.domain.User;

/**
 * ❌ PROBLEM: Hardcoded if-else — adding any new permission requires modifying this class.
 * Cannot compose permissions. Cannot reuse sub-rules.
 */
public class BadAuthorizationService {

    public boolean canAccessHrModule(User user) {
        // ❌ Every new requirement means a new if-else branch in this class
        if (user.roles().contains("ADMIN")) {
            return true;
        } else if (user.roles().contains("MANAGER") && user.directPermissions().contains("HR_WRITE")) {
            return true;
        } else if (user.directPermissions().contains("READ") && user.directPermissions().contains("HR_READ")) {
            return true;
        }
        return false;
    }

    public boolean canDeleteOrder(User user) {
        // ❌ Duplicated role check — cannot reuse "isAdmin" rule from canAccessHrModule
        if (user.roles().contains("ADMIN")) {
            return true;
        } else if (user.directPermissions().contains("DELETE")) {
            return true;
        }
        return false;
    }
}
