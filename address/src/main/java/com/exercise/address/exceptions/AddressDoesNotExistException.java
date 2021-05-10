package com.exercise.address.exceptions;

public class AddressDoesNotExistException extends RuntimeException {

    public AddressDoesNotExistException() {
        super();
    }

    public AddressDoesNotExistException(final String message) {
        super(message);
    }

}
