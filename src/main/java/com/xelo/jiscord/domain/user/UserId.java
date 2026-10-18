package com.xelo.jiscord.domain.user;

import java.util.UUID;

public record UserId(UUID value) {

    public UserId {
        if (value == null) {
            throw new IllegalArgumentException("UserID can't be null");
        }
    }

}
