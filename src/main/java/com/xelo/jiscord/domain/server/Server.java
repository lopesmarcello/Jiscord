package com.xelo.jiscord.domain.server;

import com.xelo.jiscord.domain.user.UserId;


public class Server {

    private final ServerId id;
    private UserId ownerId;
    private String name;
    // List<Channel>


    public Server(String name, UserId ownerId, ServerId serverId) {
        validateServerName(name);
        validateId(ownerId, "owner");
        validateId(serverId, "server");

        this.id = serverId;
        this.ownerId = ownerId;
        this.name = name;

    }

    public void rename(String newName) {
        validateServerName(newName);
        if (!newName.equals(this.name)) {
            this.name = newName;
        }
    }

    public String getName() {
        return this.name;
    }


    public ServerId getServerID() {
        return this.id;
    }


    public UserId getOwnerId() {
        return this.ownerId;
    }


    private void validateServerName(String name) {
        if (name == null || name.isBlank() || name.length() > 100) {
            throw new IllegalArgumentException("Invalid name");
        }

    }

    private <T> void validateId(T id, String name) {
        if (id == null) {
            throw new IllegalArgumentException(name + " ID can't be null");
        }
    }
}
