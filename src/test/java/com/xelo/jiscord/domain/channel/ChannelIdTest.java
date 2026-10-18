package com.xelo.jiscord.domain.channel;

import com.xelo.jiscord.domain.server.ServerId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ChannelIdTest {

    @Test
    void shouldCreateChannelIdWithValidUUID() {
        UUID validId = UUID.randomUUID();
        ChannelId cid = new ChannelId(validId);

        assertEquals(validId, cid.value());
    }

    @Test
    void shouldRejectNullUUID() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new ChannelId(null)
        );
    }

    @Test
    void shouldConsiderSameUUIDsEqual() {
        UUID id = UUID.randomUUID();
        ChannelId first = new ChannelId(id);
        ChannelId second = new ChannelId(id);

        assertEquals(first, second);
    }

    @Test
    void shouldConsiderDifferentUUIDsNotEqual() {
        UUID firstUuid = UUID.randomUUID();
        UUID secondUuid = UUID.randomUUID();

        ChannelId first = new ChannelId(firstUuid);
        ChannelId second = new ChannelId(secondUuid);

        assertNotEquals(first, second);
    }
}

