package com.exercise.identification.exceptions;

public class IdentificationAlreadyExistsException extends RuntimeException {

    public IdentificationAlreadyExistsException() {
        super();
    }

    public IdentificationAlreadyExistsException(final String message) {
        super(message);
    }

}
