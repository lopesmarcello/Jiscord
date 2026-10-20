package com.xelo.jiscord.infrastructure.persistence.inMemory;

import com.xelo.jiscord.application.ports.out.ChannelRepository;
import com.xelo.jiscord.domain.channel.Channel;
import com.xelo.jiscord.domain.channel.ChannelId;
import com.xelo.jiscord.domain.server.ServerId;

import java.util.HashMap;
import java.util.Map;

public class InMemoryChannelRepository implements ChannelRepository {

    Map<ChannelId, Channel> db = new HashMap<>();

    @Override
    public boolean existsByServerIdAndName(ServerId serverId, String name) {
        return db.values().stream().anyMatch(item ->
                item.getServerId().equals(serverId) &&
                        item.getName().equals(name));
    }

    @Override
    public void save(Channel channel) {
        if (channel == null) {
            throw new IllegalArgumentException("Channel cannot be null");
        }

        db.put(channel.getChannelId(), channel);
    }

}
