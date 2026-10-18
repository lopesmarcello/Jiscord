package com.xelo.jiscord.domain.server;

import java.util.UUID;

public record ServerId(UUID value) {

    public ServerId {
        if (value == null) {
            throw new IllegalArgumentException("Server ID can't be null.");
        }
    }
}
