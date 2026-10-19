package com.xelo.jiscord.application.channel;

import com.xelo.jiscord.application.ports.out.ServerRepository;
import com.xelo.jiscord.domain.server.ServerId;

public class FakeServerRepository implements ServerRepository {
    private boolean exists = false;

    public boolean setExists(boolean exist) {
        this.exists = exist;
        return this.exists;
    }

    @Override
    public boolean existsById(ServerId serverId) {
        return false;
    }
}
