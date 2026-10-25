package com.xelo.jiscord.infrastructure.web.exception;

import com.xelo.jiscord.application.exception.DuplicatedChannelException;
import com.xelo.jiscord.application.exception.ServerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleServerNotFound(
            ServerNotFoundException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleDuplicatedChannel(
            DuplicatedChannelException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }
}
