package com.xelo.jiscord.infrastructure.web.channel;

import com.xelo.jiscord.application.channel.CreateChannel;
import com.xelo.jiscord.application.channel.CreateChannelCommand;
import com.xelo.jiscord.domain.channel.ChannelId;
import com.xelo.jiscord.domain.server.ServerId;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {
    private final CreateChannel createChannel;

    public ChannelController(CreateChannel createChannel) {
        this.createChannel = createChannel;
    }

    @PostMapping
    public String create(@RequestBody CreateChannelRequest request) {
        ServerId serverId = new ServerId(request.serverId());

        CreateChannelCommand command = new CreateChannelCommand(
                serverId,
                request.name(),
                request.type()
        );

        ChannelId createdChannelId = createChannel.execute(command);

        return "Canal criado! id: " + createdChannelId.value();
    }
}
