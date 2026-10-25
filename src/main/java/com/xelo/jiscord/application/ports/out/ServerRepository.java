package com.xelo.jiscord.application.ports.out;

import com.xelo.jiscord.domain.server.Server;
import com.xelo.jiscord.domain.server.ServerId;

public interface ServerRepository {
    boolean existsById(ServerId serverId);

    void save(Server server);
}
