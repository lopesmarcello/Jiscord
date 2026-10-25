package com.xelo.jiscord.application;

import com.xelo.jiscord.application.ports.out.ChannelRepository;
import com.xelo.jiscord.domain.channel.Channel;
import com.xelo.jiscord.domain.server.ServerId;

public class FakeChannelRepository implements ChannelRepository {
    private boolean exists = false;
    private boolean saveCalled = false;

    private Channel savedChannel;

    @Override
    public boolean existsByServerIdAndName(ServerId serverId, String name) {
        return exists;
    }

    @Override
    public void save(Channel channel) {
        saveCalled = true;
        savedChannel = channel;
    }

    public void setExists(boolean exist) {
        this.exists = exist;
    }

    public boolean getSaveCalled() {
        return this.saveCalled;
    }

    public Channel getSavedChannel() {
        return savedChannel;
    }
}
