package com.xelo.jiscord.infrastructure.config;

import com.xelo.jiscord.application.ports.out.ChannelRepository;
import com.xelo.jiscord.application.ports.out.ServerRepository;
import com.xelo.jiscord.infrastructure.persistence.inMemory.InMemoryChannelRepository;
import com.xelo.jiscord.infrastructure.persistence.inMemory.InMemoryServerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfiguration {

    @Bean
    public ServerRepository serverRepository() {
        return new InMemoryServerRepository();
    }

    @Bean
    public ChannelRepository channelRepository() {
        return new InMemoryChannelRepository();
    }
}
