package com.xelo.jiscord.application.ports.out;

import com.xelo.jiscord.domain.channel.Channel;
import com.xelo.jiscord.domain.server.ServerId;

public interface ChannelRepository {
    boolean existsByServerIdAndName(ServerId serverId, String name);

    void save(Channel channel);
}
