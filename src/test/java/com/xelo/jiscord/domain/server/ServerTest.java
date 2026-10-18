package com.xelo.jiscord.domain.server;

import com.xelo.jiscord.domain.user.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ServerTest {
    private ServerId serverId;
    private UserId ownerId;

    @BeforeEach
    void setUp() {
        serverId = new ServerId(UUID.randomUUID());
        ownerId = new UserId(UUID.randomUUID());
    }

    @Test
    void shouldCreateServerWithValidData() {
        String serverName = "Java dos Cria";

        Server server = new Server(serverName, ownerId, serverId);

        assertEquals(serverName, server.getName());
        assertEquals(serverId, server.getServerID());
        assertEquals(ownerId, server.getOwnerId());
    }

    @Test
    void shouldRejectNullServerName() {
        String serverName = null;

        assertThrows(
                IllegalArgumentException.class,
                () -> new Server(serverName, ownerId, serverId)
        );
    }

    @Test
    void shouldBeAbleToRenameWithValidName() {
        String serverFirstName = "Java dos Cria";
        String serverNewName = "Boca dos Java";

        Server server = new Server(serverFirstName, ownerId, serverId);

        assertEquals(serverFirstName, server.getName());

        server.rename(serverNewName);

        assertNotEquals(serverFirstName, server.getName());
        assertEquals(serverNewName, server.getName());
    }

    @Test
    void shouldRejectNullServerRename() {
        String serverName = "BDJ";
        Server server = new Server(serverName, ownerId, serverId);

        assertThrows(
                IllegalArgumentException.class,
                () -> server.rename(null)
        );

        assertEquals(serverName, server.getName());
    }

    @Test
    void shouldNotBeAbleToHaveBlankName() {
        String serverName = "     ";

        assertThrows(
                IllegalArgumentException.class,
                () -> new Server(serverName, ownerId, serverId)
        );
    }

    @Test
    void shouldNotBeAbleToRenameAsBlank() {
        String serverName = "valid";
        Server server = new Server(serverName, ownerId, serverId);

        assertThrows(
                IllegalArgumentException.class,
                () -> server.rename("       ")
        );

        assertEquals(serverName, server.getName());
    }

    @Test
    void shouldNotBeAbleToHaveOver100charactersName() {
        String hundredCharName = "a".repeat(100);

        assertThrows(
                IllegalArgumentException.class,
                () -> new Server(hundredCharName + "a", ownerId, serverId)
        );
    }

    @Test
    void shouldNotBeAbleToHaveOver100charactersRename() {
        String hundredCharName = "a".repeat(100);
        Server server = new Server(hundredCharName, ownerId, serverId);

        assertThrows(
                IllegalArgumentException.class,
                () -> server.rename(hundredCharName + "a")
        );

        assertEquals(hundredCharName, server.getName());
    }

    @Test
    void shouldRejectNullIds() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Server("Java", null, serverId)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new Server("Java", ownerId, null)
        );
    }

}
