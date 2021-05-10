package com.exercise.address.exceptions;

public class InvalidUuidException extends RuntimeException {

    public InvalidUuidException() {
        super();
    }

    public InvalidUuidException(final String message) {
        super(message);
    }

}
