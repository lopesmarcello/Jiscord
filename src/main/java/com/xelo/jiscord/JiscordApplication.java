package com.xelo.jiscord;

import com.xelo.jiscord.application.channel.CreateChannel;
import com.xelo.jiscord.application.channel.CreateChannelCommand;
import com.xelo.jiscord.domain.channel.ChannelId;
import com.xelo.jiscord.domain.channel.ChannelType;
import com.xelo.jiscord.domain.server.Server;
import com.xelo.jiscord.domain.server.ServerId;
import com.xelo.jiscord.domain.user.UserId;
import com.xelo.jiscord.infrastructure.persistence.inMemory.InMemoryChannelRepository;
import com.xelo.jiscord.infrastructure.persistence.inMemory.InMemoryServerRepository;

import java.util.UUID;

public class JiscordApplication {
    public static void main(String[] args) {
        InMemoryChannelRepository channelRepository = new InMemoryChannelRepository();
        InMemoryServerRepository serverRepository = new InMemoryServerRepository();

        CreateChannel createChannel = new CreateChannel(
                serverRepository, channelRepository
        );

        runDemo(
                serverRepository,
                channelRepository,
                createChannel
        );
    }

    private static void runDemo(
            InMemoryServerRepository serverRepository,
            InMemoryChannelRepository channelRepository,
            CreateChannel createChannel
    ) {
        UserId userId = new UserId(UUID.randomUUID());
        ServerId serverId = new ServerId(UUID.randomUUID());
        String serverName = "Boca dos Cria";
        String channelName = "geral";

        Server server = new Server(
                serverName,
                userId,
                serverId
        );
        serverRepository.save(server);


        CreateChannelCommand createChannelCommand = new CreateChannelCommand(
                serverId, channelName, ChannelType.TEXT
        );

        ChannelId createdChannelId = createChannel.execute(createChannelCommand);

        System.out.println("Canal criado! ID: " + createdChannelId.value());
        System.out.println("exists? " + channelRepository.existsByServerIdAndName(serverId, channelName));
    }
}
