package com.xelo.jiscord.infrastructure.persistence.inMemory;

import com.xelo.jiscord.application.ports.out.ServerRepository;
import com.xelo.jiscord.domain.server.Server;
import com.xelo.jiscord.domain.server.ServerId;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InMemoryServerRepository implements ServerRepository {
    private final Map<ServerId, Server> db = new HashMap<>();

    @Override
    public boolean existsById(ServerId serverId) {
        return db.containsKey(serverId);
    }

    public void save(Server server) {
        if (Objects.isNull(server)) {
            throw new IllegalArgumentException("Server can't be null");
        }

        db.put(server.getServerID(), server);
    }

}
