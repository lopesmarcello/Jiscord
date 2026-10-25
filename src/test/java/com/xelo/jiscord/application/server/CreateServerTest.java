package com.xelo.jiscord.application.server;

import com.xelo.jiscord.application.FakeChannelRepository;
import com.xelo.jiscord.application.FakeServerRepository;
import com.xelo.jiscord.domain.server.ServerId;
import com.xelo.jiscord.domain.user.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CreateServerTest {
    FakeServerRepository serverRepository;

    private String serverName = "Boca dos Cria";
    private CreateServer createServer;
    private UserId userId;

    @BeforeEach
    void setup() {
        this.serverRepository = new FakeServerRepository();
        this.userId = new UserId(UUID.randomUUID());

        this.createServer = new CreateServer(
                serverRepository
        );
    }

    @Test
    void shouldRejectServerCreationWithIdAlreadyExisting() {
        serverRepository.setExists(true);
        CreateServerCommand command = new CreateServerCommand(userId, serverName);
        assertThrows(
                IllegalArgumentException.class,
                () -> createServer.execute(command)
        );
        assertFalse(serverRepository.getSaveCalled());
    }

    @Test
    void shouldCreateServerWithValidData() {
        CreateServerCommand command = new CreateServerCommand(userId, serverName);
        ServerId createdServerId = createServer.execute(command);
        assertNotNull(createdServerId);
        assertTrue(serverRepository.getSaveCalled());
    }


}
