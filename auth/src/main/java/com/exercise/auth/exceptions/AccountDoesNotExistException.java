package com.exercise.auth.exceptions;

public class AccountDoesNotExistException extends RuntimeException {

    public AccountDoesNotExistException() {
        super();
    }

    public AccountDoesNotExistException(final String message) {
        super(message);
    }

}
