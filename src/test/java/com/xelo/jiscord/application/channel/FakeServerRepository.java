package com.xelo.jiscord.application.channel;

import com.xelo.jiscord.application.ports.out.ServerRepository;
import com.xelo.jiscord.domain.server.ServerId;

public class FakeServerRepository implements ServerRepository {
    private boolean exists = false;

    public void setExists(boolean exist) {
        this.exists = exist;
    }

    @Override
    public boolean existsById(ServerId serverId) {
        return exists;
    }
}
