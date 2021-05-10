package com.exercise.communication.exceptions;

public class CommunicationDoesNotExistException extends RuntimeException {

    public CommunicationDoesNotExistException() {
        super();
    }

    public CommunicationDoesNotExistException(final String message) {
        super(message);
    }

}
