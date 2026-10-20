package com.xelo.jiscord.domain.channel;

import com.xelo.jiscord.domain.server.ServerId;

import java.util.Objects;

public class Channel {
    private final ChannelId channelId;
    private final ServerId serverId;
    private String name;
    private final ChannelType channelType;

    public Channel(String name, ChannelType channelType, ServerId serverId, ChannelId channelId) {
        validateChannelName(name);
        validateId(serverId, "server");
        validateId(channelId, "channel");

        if (channelType == null) {
            throw new IllegalArgumentException("Invalid channel type");
        }

        this.channelId = channelId;
        this.serverId = serverId;
        this.name = name;
        this.channelType = channelType;
    }

    public void rename(String newName) {
        validateChannelName(newName);
        if (!Objects.equals(newName, this.name)) {
            this.name = newName;
        }
    }

    public ChannelId getChannelId() {
        return this.channelId;
    }

    public ServerId getServerId() {
        return this.serverId;
    }

    public String getName() {
        return this.name;
    }

    public ChannelType getChannelType() {
        return this.channelType;
    }

    private void validateChannelName(String name) {
        if (name == null || name.isBlank() || name.length() > 100) {
            throw new IllegalArgumentException("Invalid name");
        }
    }

    private <T> void validateId(T id, String name) {
        if (Objects.equals(id, null)) {
            throw new IllegalArgumentException(name + " " + "ID cannot be null");
        }
    }
}
