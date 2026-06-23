package org.epamcampus.dp.structural.composite.pattern;

import org.epamcampus.dp.structural.composite.domain.User;

public interface Permission {
    boolean isGranted(User user);
    String describe();
}
