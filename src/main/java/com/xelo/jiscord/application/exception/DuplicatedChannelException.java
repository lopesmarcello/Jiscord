package com.xelo.jiscord.application.exception;

public class DuplicatedChannelException extends RuntimeException {
    public DuplicatedChannelException(String message) {
        super(message);
    }
}
