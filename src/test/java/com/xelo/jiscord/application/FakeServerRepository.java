package com.xelo.jiscord.application;

import com.xelo.jiscord.application.ports.out.ServerRepository;
import com.xelo.jiscord.domain.server.Server;
import com.xelo.jiscord.domain.server.ServerId;

public class FakeServerRepository implements ServerRepository {
    private boolean exists = false;
    private boolean saveCalled = false;

    private Server savedServer;

    public void setExists(boolean exist) {
        this.exists = exist;
    }

    @Override
    public boolean existsById(ServerId serverId) {
        return exists;
    }

    @Override
    public void save(Server server) {
        this.saveCalled = true;
        this.savedServer = server;
    }

    public boolean getSaveCalled() {
        return this.saveCalled;
    }

    public Server getSavedServer() {
        return this.savedServer;
    }
}
