package com.xelo.jiscord.application.server;

import com.xelo.jiscord.domain.server.ServerId;
import com.xelo.jiscord.domain.user.UserId;

public record CreateServerCommand(
        UserId ownerId,
        String name
) {
}
