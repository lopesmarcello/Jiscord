package com.xelo.jiscord.application.channel;

import com.xelo.jiscord.application.ports.out.ChannelRepository;
import com.xelo.jiscord.application.ports.out.ServerRepository;

public interface CreateChannel {
    final ServerRepository serverRepository;
    final ChannelRepository channelRepository;
}
