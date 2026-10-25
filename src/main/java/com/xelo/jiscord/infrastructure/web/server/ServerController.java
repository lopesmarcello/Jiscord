package com.xelo.jiscord.infrastructure.web.server;

import com.xelo.jiscord.application.server.CreateServer;
import com.xelo.jiscord.application.server.CreateServerCommand;
import com.xelo.jiscord.domain.server.ServerId;
import com.xelo.jiscord.domain.user.UserId;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/servers")
public class ServerController {
    private final CreateServer createServer;

    public ServerController(CreateServer createServer) {
        this.createServer = createServer;
    }

    @PostMapping
    public String create(@RequestBody CreateServerRequest request) {
        UserId ownerId = new UserId(request.ownerId());

        CreateServerCommand command = new CreateServerCommand(ownerId, request.name());

        ServerId createdServerId = createServer.execute(command);

        return "Server criado! id: " + createdServerId.value();
    }
}
