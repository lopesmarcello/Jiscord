package com.xelo.jiscord.application.channel;

import com.xelo.jiscord.domain.channel.ChannelType;
import com.xelo.jiscord.domain.server.ServerId;

public record CreateChannelCommand(
        ServerId serverId,
        String name,
        ChannelType type
) {
}
