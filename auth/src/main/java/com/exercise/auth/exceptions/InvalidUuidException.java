package com.exercise.auth.exceptions;

public class InvalidUuidException extends RuntimeException {

    public InvalidUuidException() {
        super();
    }

    public InvalidUuidException(final String message) {
        super(message);
    }

}
