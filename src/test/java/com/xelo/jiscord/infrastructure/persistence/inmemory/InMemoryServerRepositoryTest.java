package com.xelo.jiscord.infrastructure.persistence.inmemory;

import com.xelo.jiscord.domain.server.Server;
import com.xelo.jiscord.domain.server.ServerId;
import com.xelo.jiscord.domain.user.UserId;
import com.xelo.jiscord.infrastructure.persistence.inMemory.InMemoryServerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryServerRepositoryTest {
    InMemoryServerRepository inMemoryServerRepository;
    ServerId serverId;
    Server server;
    UserId userId;

    @BeforeEach
    void setup() {
        this.inMemoryServerRepository = new InMemoryServerRepository();
        this.serverId = new ServerId(UUID.randomUUID());
        this.userId = new UserId(UUID.randomUUID());
        this.server = new Server("bdc", userId, serverId);
        inMemoryServerRepository.save(this.server);
    }

    @Test
    void shouldFindExistingServer() {
        assertTrue(
                inMemoryServerRepository.existsById(serverId)
        );
    }


    @Test
    void shouldNotFindUnknownServer() {
        ServerId randomServerId = new ServerId(UUID.randomUUID());
        assertFalse(
                inMemoryServerRepository.existsById(randomServerId)
        );
    }


    @Test
    void shouldRejectNullServer() {
        assertThrows(
                IllegalArgumentException.class,
                () -> inMemoryServerRepository.save(null)
        );
    }

}
