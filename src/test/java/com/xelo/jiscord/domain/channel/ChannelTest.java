package com.xelo.jiscord.domain.channel;

import com.xelo.jiscord.domain.server.ServerId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChannelTest {
    ChannelId channelId;
    ServerId serverId;
    ChannelType channelType;
    String channelName;

    @BeforeEach
    void setup() {
        channelId = new ChannelId(UUID.randomUUID());
        serverId = new ServerId(UUID.randomUUID());
        channelType = ChannelType.TEXT;
        channelName = "geral";
    }

    @Test
    void shouldCreateChannelWithValidParameters() {
        Channel chan = new Channel(
                channelName,
                channelType,
                serverId,
                channelId
        );

        assertEquals(channelName, chan.getName());
        assertEquals(channelId, chan.getChannelId());
        assertEquals(channelType, chan.getChannelType());
    }

    @Test
    void shouldNotBeAbleToCreateChannelWithNullName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Channel(
                        null,
                        channelType,
                        serverId,
                        channelId
                )
        );
    }

    @Test
    void shouldNotBeAbleToCreateChannelWithBlankName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Channel(
                        "    ",
                        channelType,
                        serverId,
                        channelId
                )
        );
    }

    @Test
    void shouldNotBeAbleToCreateChannelWithNullServerId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Channel(
                        channelName,
                        channelType,
                        null,
                        channelId
                )
        );
    }

    @Test
    void shouldNotBeAbleToCreateChannelWithNullChannelId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Channel(
                        channelName,
                        channelType,
                        serverId,
                        null
                )
        );
    }

    @Test
    void shouldNotBeAbleToCreateChannelWithNullChannelType() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Channel(
                        channelName,
                        null,
                        serverId,
                        channelId
                )
        );
    }

    @Test
    void shouldBeAbleToAccept100CharName() {
        String hundredCharName = "a".repeat(99);
        Channel chan = new Channel(
                hundredCharName,
                channelType,
                serverId,
                channelId
        );

        assertEquals(hundredCharName, chan.getName());
    }

    @Test
    void shouldNotBeAbleToCreateAChannelOver100CharName() {
        String hundredCharName = "a".repeat(100);
        assertThrows(
                IllegalArgumentException.class,
                () -> new Channel(
                        hundredCharName + "a",
                        channelType,
                        serverId,
                        channelId
                )
        );
    }

    @Test
    void shouldBeAbleToRenameCorrectly() {
        String newName = "general";
        Channel chan = new Channel(
                channelName,
                channelType,
                serverId,
                channelId
        );

        assertEquals(channelName, chan.getName());

        chan.rename(newName);

        assertEquals(newName, chan.getName());
    }

    @Test
    void shouldBeAbleToPreserveNameCorrectlyAfterInvalidTry() {
        String newName = null;
        Channel chan = new Channel(
                channelName,
                channelType,
                serverId,
                channelId
        );

        assertEquals(channelName, chan.getName());

        assertThrows(
                IllegalArgumentException.class,
                () -> chan.rename(newName)
        );

        assertEquals(channelName, chan.getName());
    }

    @Test
    void shouldPreserveChannelTypeAfterRenaming() {
        String newName = "NewName";
        Channel chan = new Channel(
                channelName,
                channelType,
                serverId,
                channelId
        );

        assertEquals(channelName, chan.getName());
        chan.rename(newName);
        assertEquals(newName, chan.getName());
        assertEquals(channelType, chan.getChannelType());
    }
}
