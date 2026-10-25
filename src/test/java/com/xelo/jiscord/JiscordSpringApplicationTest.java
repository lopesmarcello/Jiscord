package com.xelo.jiscord;

import com.xelo.jiscord.application.channel.CreateChannel;
import com.xelo.jiscord.application.ports.out.ChannelRepository;
import com.xelo.jiscord.application.ports.out.ServerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.AssertionsKt.assertNotNull;

@SpringBootTest
public class JiscordSpringApplicationTest {
    @Autowired
    ServerRepository serverRepository;

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    CreateChannel createChannel;

    @Test
    void shouldHaveServerRepository() {
        assertNotNull(serverRepository);
    }

    @Test
    void shouldHaveChannelRepository() {
        assertNotNull(channelRepository);
    }

    @Test
    void shouldHaveCreatechannel() {
        assertNotNull(createChannel);
    }
}
