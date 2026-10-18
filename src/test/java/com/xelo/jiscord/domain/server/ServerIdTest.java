package com.xelo.jiscord.domain.server;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ServerIdTest {

    @Test
    void shouldCreateServerIdWithValidUUID() {
        UUID validId = UUID.randomUUID();
        ServerId sid = new ServerId(validId);

        assertEquals(validId, sid.value());
    }

    @Test
    void shouldRejectNullUUID() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ServerId(null)
        );
    }

    @Test
    void shouldConsiderSameUUIDsEqual() {
        UUID id = UUID.randomUUID();
        ServerId first = new ServerId(id);
        ServerId second = new ServerId(id);

        assertEquals(first, second);
    }

    @Test
    void shouldConsiderDifferentUUIDsNotEqual() {
        UUID firstUuid = UUID.randomUUID();
        UUID secondUuid = UUID.randomUUID();

        ServerId first = new ServerId(firstUuid);
        ServerId second = new ServerId(secondUuid);

        assertNotEquals(first, second);
    }
}
