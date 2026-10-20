package com.xelo.jiscord.application.channel;

import com.xelo.jiscord.application.ports.out.ChannelRepository;
import com.xelo.jiscord.application.ports.out.ServerRepository;
import com.xelo.jiscord.domain.channel.Channel;
import com.xelo.jiscord.domain.channel.ChannelId;

import java.util.Objects;
import java.util.UUID;

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

    public ChannelId execute(CreateChannelCommand command) {
        if (command == null) {
            throw new IllegalArgumentException(
                    "Command can't be null"
            );
        }

        if (!serverRepository.existsById(command.serverId())) {
            throw new IllegalArgumentException("Server ID does not exist");
        }

        if (channelRepository.existsByServerIdAndName(command.serverId(), command.name())) {
            throw new IllegalArgumentException("Channel already exists");
        }

        ChannelId channelId = new ChannelId(UUID.randomUUID());
        Channel chan = new Channel(
                command.name(),
                command.type(),
                command.serverId(),
                channelId
        );

        channelRepository.save(chan);
        return chan.getChannelId();
    }
}
