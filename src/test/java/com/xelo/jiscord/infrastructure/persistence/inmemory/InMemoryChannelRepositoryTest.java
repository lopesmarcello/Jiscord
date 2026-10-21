package com.xelo.jiscord.infrastructure.persistence.inmemory;

import com.xelo.jiscord.domain.channel.Channel;
import com.xelo.jiscord.domain.channel.ChannelId;
import com.xelo.jiscord.domain.channel.ChannelType;
import com.xelo.jiscord.domain.server.ServerId;
import com.xelo.jiscord.infrastructure.persistence.inMemory.InMemoryChannelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryChannelRepositoryTest {
    InMemoryChannelRepository channelRepository;

    ServerId serverId;
    ChannelId channelId;
    Channel channel;


    @BeforeEach
    void setup() {
        this.channelRepository = new InMemoryChannelRepository();
        this.serverId = new ServerId(UUID.randomUUID());
        this.channelId = new ChannelId(UUID.randomUUID());
        this.channel = new Channel("geral", ChannelType.TEXT, serverId, channelId);
    }

    @Test
    void shouldFindSavedChannel() {
        channelRepository.save(channel);
        boolean exists = channelRepository.existsByServerIdAndName(serverId, "geral");
        assertTrue(exists);
    }

    @Test
    void shouldReturnFalseForUnknownChannel() {
        ServerId wrongServerId = new ServerId(UUID.randomUUID());
        channelRepository.save(channel);
        boolean exists = channelRepository.existsByServerIdAndName(wrongServerId, "geral");
        assertFalse(exists);
    }

    @Test
    void shouldAllowSameNameInDifferentServers() {
        ServerId otherServerId = new ServerId(UUID.randomUUID());
        ChannelId otherChannelId = new ChannelId(UUID.randomUUID());
        Channel otherChannel = new Channel("geral", ChannelType.TEXT, otherServerId, otherChannelId);
        assertDoesNotThrow(() -> channelRepository.save(otherChannel));
        channelRepository.existsByServerIdAndName(serverId, "geral");
        channelRepository.existsByServerIdAndName(otherServerId, "geral");
    }

    @Test
    void shouldRejectNullChannel() {
        assertThrows(IllegalArgumentException.class,
                () -> channelRepository.save(null));
    }
}
