package com.xelo.jiscord.application.channel;

import com.xelo.jiscord.application.ports.out.ServerRepository;
import com.xelo.jiscord.domain.channel.ChannelType;
import com.xelo.jiscord.domain.server.ServerId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateChannelTest {
    ServerRepository serverRepository;
    FakeChannelRepository channelRepository;

    @BeforeEach
    void setup() {
        this.serverRepository = new ServerRepository() {
            @Override
            public boolean existsById(ServerId serverId) {
                return false;
            }
        };

        this.channelRepository = new FakeChannelRepository();
    }

    @Test
    void shouldRejectChannelCreationWhenServerDoesNotExist() {
        ServerId svId = new ServerId(UUID.randomUUID());
        CreateChannelCommand cmd = new CreateChannelCommand(
                svId, "teste", ChannelType.TEXT
        );

        CreateChannel createChannel = new CreateChannel(serverRepository, channelRepository);

        assertThrows(
                IllegalArgumentException.class,
                () -> createChannel.execute(cmd)
        );

        assertFalse(channelRepository.getSaveCalled());
    }
}
