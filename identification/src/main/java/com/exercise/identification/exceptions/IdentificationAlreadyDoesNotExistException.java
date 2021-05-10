package com.exercise.identification.exceptions;

public class IdentificationAlreadyDoesNotExistException extends RuntimeException {

    public IdentificationAlreadyDoesNotExistException() {
        super();
    }

    public IdentificationAlreadyDoesNotExistException(final String message) {
        super(message);
    }

}
