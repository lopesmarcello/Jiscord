package com.xelo.jiscord.infrastructure.web.server;

import java.util.UUID;

public record CreateServerRequest(
        UUID ownerId,
        String name
) {
}
