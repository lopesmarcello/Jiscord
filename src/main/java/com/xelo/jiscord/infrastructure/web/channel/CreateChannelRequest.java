package com.xelo.jiscord.infrastructure.web.channel;

import com.xelo.jiscord.domain.channel.ChannelType;

import java.util.UUID;

public record CreateChannelRequest(
        UUID serverId,
        String name,
        ChannelType type
) {
}
