package com.xelo.jiscord.application.channel;

import com.xelo.jiscord.application.ports.out.ChannelRepository;
import com.xelo.jiscord.application.ports.out.ServerRepository;

import java.util.Objects;

public class CreateChannel {
    private final ServerRepository serverRepository;
    private final ChannelRepository channelRepository;

    public CreateChannel(ServerRepository serverRepository, ChannelRepository channelRepository) {
        if (Objects.equals(null, serverRepository)) {
            throw new IllegalArgumentException("Server Repository can't be null");
        }
        if (Objects.equals(null, channelRepository)) {
            throw new IllegalArgumentException("Channel Repository can't be null");
        }

        this.serverRepository = serverRepository;
        this.channelRepository = channelRepository;
    }

    public execute(CreateChannelCommand command) {

    }
}
