package com.xelo.jiscord.application.server;

import com.xelo.jiscord.application.ports.out.ServerRepository;
import com.xelo.jiscord.domain.server.Server;
import com.xelo.jiscord.domain.server.ServerId;

import java.util.Objects;
import java.util.UUID;

public class CreateServer {
    private final ServerRepository repository;

    public CreateServer(ServerRepository serverRepository) {
        if (Objects.equals(null, serverRepository)) {
            throw new IllegalArgumentException("Server repository cannot be null");
        }

        this.repository = serverRepository;
    }


    public ServerId execute(CreateServerCommand command) {
        if (command == null) {
            throw new IllegalArgumentException(
                    "Command can't be null"
            );
        }

        ServerId serverId = new ServerId(UUID.randomUUID());

        if (repository.existsById(serverId)) {
            throw new IllegalArgumentException(
                    "Server ID already exists"
            );
        }
        Server server = new Server(
                command.name(),
                command.ownerId(),
                serverId
        );

        repository.save(server);
        return server.getServerId();
    }

}
