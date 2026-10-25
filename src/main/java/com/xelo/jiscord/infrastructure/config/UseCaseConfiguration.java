package com.xelo.jiscord.infrastructure.config;

import com.xelo.jiscord.application.channel.CreateChannel;
import com.xelo.jiscord.application.ports.out.ChannelRepository;
import com.xelo.jiscord.application.ports.out.ServerRepository;
import com.xelo.jiscord.application.server.CreateServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public CreateChannel createChannel(
            ServerRepository serverRepository,
            ChannelRepository channelRepository
    ) {
        return new CreateChannel(serverRepository, channelRepository);
    }

    @Bean
    public CreateServer createServer(
            ServerRepository serverRepository
    ) {
        return new CreateServer(serverRepository);
    }
}
