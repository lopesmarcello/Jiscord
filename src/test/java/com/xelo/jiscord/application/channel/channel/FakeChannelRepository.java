package com.xelo.jiscord.application.channel.channel;

import com.xelo.jiscord.application.ports.out.ChannelRepository;
import com.xelo.jiscord.domain.channel.Channel;
import com.xelo.jiscord.domain.server.ServerId;

public class FakeChannelRepository implements ChannelRepository {
    private boolean saveCalled = false;

    @Override
    public boolean existsByServerIdAndName(ServerId serverId, String name) {
        return false;
    }

    @Override
    public void save(Channel channel) {
        saveCalled = true;
    }

    public boolean getSaveCalled() {
        return this.saveCalled;
    }
}
