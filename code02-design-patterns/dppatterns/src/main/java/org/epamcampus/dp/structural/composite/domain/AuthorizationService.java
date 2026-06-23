package org.epamcampus.dp.structural.composite.domain;

import org.epamcampus.dp.structural.composite.pattern.Permission;

public class AuthorizationService {

    public boolean check(User user, Permission permission) {
        boolean result = permission.isGranted(user);
        System.out.println("  [AuthZ] " + user.name() + " → " + permission.describe()
                + ": " + (result ? "✅ GRANTED" : "❌ DENIED"));
        return result;
    }
}
