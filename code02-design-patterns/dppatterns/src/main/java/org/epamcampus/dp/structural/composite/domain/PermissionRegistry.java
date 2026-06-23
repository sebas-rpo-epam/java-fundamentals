package org.epamcampus.dp.structural.composite.domain;

import org.epamcampus.dp.structural.composite.pattern.Permission;
import org.epamcampus.dp.structural.composite.impl.SimplePermission;
import org.epamcampus.dp.structural.composite.impl.AllOfPermission;
import org.epamcampus.dp.structural.composite.impl.AnyOfPermission;

import java.util.List;

public class PermissionRegistry {

    public static Permission adminRole() {
        return new AllOfPermission("ADMIN_ROLE", List.of(
                new SimplePermission("CAN_READ",   u -> u.directPermissions().contains("READ")),
                new SimplePermission("CAN_WRITE",  u -> u.directPermissions().contains("WRITE")),
                new SimplePermission("CAN_DELETE", u -> u.directPermissions().contains("DELETE"))
        ));
    }

    public static Permission viewerRole() {
        return new AnyOfPermission("VIEWER_ROLE", List.of(
                new SimplePermission("IS_PREMIUM", u -> u.roles().contains("PREMIUM")),
                new SimplePermission("CAN_READ",   u -> u.directPermissions().contains("READ"))
        ));
    }

    public static Permission managerHrAccess() {
        return new AllOfPermission("MANAGER_HR_ACCESS", List.of(
                new SimplePermission("IS_MANAGER",  u -> u.roles().contains("MANAGER")),
                new SimplePermission("HAS_HR_WRITE", u -> u.directPermissions().contains("HR_WRITE"))
        ));
    }
}
