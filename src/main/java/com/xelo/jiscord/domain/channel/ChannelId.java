package com.xelo.jiscord.domain.channel;

import java.util.UUID;

public record ChannelId(UUID value) {

    public ChannelId {
        if (value == null) {
            throw new IllegalArgumentException("Channel ID cannot be null");
        }
    }
}
