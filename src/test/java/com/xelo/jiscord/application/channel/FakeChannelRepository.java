package com.xelo.jiscord.application.channel;

import com.xelo.jiscord.application.ports.out.ChannelRepository;
import com.xelo.jiscord.domain.channel.Channel;
import com.xelo.jiscord.domain.server.ServerId;

public class FakeChannelRepository implements ChannelRepository {
    private boolean exists = false;
    private boolean saveCalled = false;

    public boolean exists(boolean exist) {
        this.exists = exist;
        return this.exists;
    }

    @Override
    public boolean existsByServerIdAndName(ServerId serverId, String name) {
        return exists;
    }

    @Override
    public void save(Channel channel) {
        saveCalled = true;
    }

    public boolean getSaveCalled() {
        return this.saveCalled;
    }
}
