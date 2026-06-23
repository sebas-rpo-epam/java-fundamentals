package org.epamcampus.solid.dip;

import java.util.UUID;

public class SimpleTokenService implements TokenService {

    @Override
    public String generate(User user) {
        return "token-" + user.id() + "-" + UUID.randomUUID().toString().substring(0, 8);
    }
}
