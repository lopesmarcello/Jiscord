package com.xelo.jiscord.domain.user;


import com.xelo.jiscord.domain.server.ServerId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserIdTest {

    @Test
    void shouldCreateUserIdWithValidUUID() {
        UUID validId = UUID.randomUUID();
        UserId sid = new UserId(validId);

        assertEquals(validId, sid.value());
    }

    @Test
    void shouldRejectNullUUID() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new UserId(null)
        );
    }

    @Test
    void shouldConsiderSameUUIDsEqual() {
        UUID id = UUID.randomUUID();
        UserId first = new UserId(id);
        UserId second = new UserId(id);

        assertEquals(first, second);
    }

    @Test
    void shouldConsiderDifferentUUIDsNotEqual() {
        UUID firstUuid = UUID.randomUUID();
        UUID secondUuid = UUID.randomUUID();

        UserId first = new UserId(firstUuid);
        UserId second = new UserId(secondUuid);

        assertNotEquals(first, second);
    }
}
