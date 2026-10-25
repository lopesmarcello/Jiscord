package com.xelo.jiscord.application.channel;

import com.xelo.jiscord.application.FakeChannelRepository;
import com.xelo.jiscord.application.FakeServerRepository;
import com.xelo.jiscord.application.exception.DuplicatedChannelException;
import com.xelo.jiscord.application.exception.ServerNotFoundException;
import com.xelo.jiscord.domain.channel.Channel;
import com.xelo.jiscord.domain.channel.ChannelId;
import com.xelo.jiscord.domain.channel.ChannelType;
import com.xelo.jiscord.domain.server.ServerId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CreateChannelTest {
    FakeServerRepository serverRepository;
    FakeChannelRepository channelRepository;

    private CreateChannel createChannel;
    private ServerId serverId;

    @BeforeEach
    void setup() {
        this.serverRepository = new FakeServerRepository();
        this.channelRepository = new FakeChannelRepository();

        this.serverId = new ServerId(UUID.randomUUID());

        this.createChannel = new CreateChannel(
                serverRepository,
                channelRepository
        );
    }

    @Test
    void shouldRejectChannelCreationWhenServerDoesNotExist() {
        serverRepository.setExists(false);

        CreateChannelCommand command = new CreateChannelCommand(
                serverId,
                "geral",
                ChannelType.TEXT
        );

        ServerNotFoundException exception = assertThrows(
                ServerNotFoundException.class,
                () -> createChannel.execute(command)
        );

        assertEquals(
                "Server ID does not exist",
                exception.getMessage()
        );

        assertFalse(channelRepository.getSaveCalled());
    }

    @Test
    void shouldRejectDuplicateChannelName() {
        serverRepository.setExists(true);
        channelRepository.setExists(true);

        CreateChannelCommand command = new CreateChannelCommand(
                serverId,
                "geral",
                ChannelType.TEXT
        );

        DuplicatedChannelException exception = assertThrows(
                DuplicatedChannelException.class,
                () -> createChannel.execute(command)
        );

        assertEquals(
                "Channel already exists",
                exception.getMessage()
        );

        assertFalse(channelRepository.getSaveCalled());
    }

    @Test
    void shouldCreateChannelWhenDataIsValid() {
        serverRepository.setExists(true);
        channelRepository.setExists(false);

        CreateChannelCommand command = new CreateChannelCommand(
                serverId,
                "geral",
                ChannelType.TEXT
        );

        ChannelId commandResult = createChannel.execute(command);
        assertNotNull(commandResult);
        assertTrue(channelRepository.getSaveCalled());

        Channel savedChannel = channelRepository.getSavedChannel();
        assertNotNull(savedChannel);

        assertEquals(commandResult, savedChannel.getChannelId());
        assertEquals("geral", savedChannel.getName());
        assertEquals(ChannelType.TEXT, savedChannel.getChannelType());
    }

    @Test
    void shouldRejectNullCommand() {
        assertThrows(
                IllegalArgumentException.class,
                () -> createChannel.execute(null)
        );

        assertFalse(channelRepository.getSaveCalled());
    }

}
