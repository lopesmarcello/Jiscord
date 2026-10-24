package com.xelo.jiscord.integration;

import com.xelo.jiscord.application.channel.CreateChannel;
import com.xelo.jiscord.application.channel.CreateChannelCommand;
import com.xelo.jiscord.domain.channel.ChannelId;
import com.xelo.jiscord.domain.channel.ChannelType;
import com.xelo.jiscord.domain.server.Server;
import com.xelo.jiscord.domain.server.ServerId;
import com.xelo.jiscord.domain.user.UserId;
import com.xelo.jiscord.infrastructure.persistence.inMemory.InMemoryChannelRepository;
import com.xelo.jiscord.infrastructure.persistence.inMemory.InMemoryServerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CreateChannelIntegrationTest {
    private InMemoryServerRepository inMemoryServerRepository;
    private InMemoryChannelRepository inMemoryChannelRepository;
    private UserId userId;
    private ServerId serverId;
    private Server server;

    @BeforeEach
    void setup() {
        this.inMemoryServerRepository = new InMemoryServerRepository();
        this.inMemoryChannelRepository = new InMemoryChannelRepository();
        this.userId = new UserId(UUID.randomUUID());
        this.serverId = new ServerId(UUID.randomUUID());
        this.server = new Server("bdc", userId, serverId);
        inMemoryServerRepository.save(server);
    }


    @Test
    void shouldCreateChannelWithValidData() {
        CreateChannel createChannel = new CreateChannel(
                inMemoryServerRepository,
                inMemoryChannelRepository
        );

        CreateChannelCommand createChannelCommand = new CreateChannelCommand(
                serverId,
                "geral",
                ChannelType.TEXT
        );

        createChannel.execute(createChannelCommand);

        assertTrue(inMemoryChannelRepository.existsByServerIdAndName(serverId, "geral"));
    }

    @Test
    void shouldRejectDuplicateChannelName() {
        CreateChannel createChannel = new CreateChannel(
                inMemoryServerRepository,
                inMemoryChannelRepository
        );

        CreateChannelCommand createChannelCommand = new CreateChannelCommand(
                serverId,
                "geral",
                ChannelType.TEXT
        );

        createChannel.execute(createChannelCommand);
        assertThrows(IllegalArgumentException.class, () -> createChannel.execute(createChannelCommand));
        assertTrue(inMemoryChannelRepository.existsByServerIdAndName(serverId, "geral"));

    }

}
