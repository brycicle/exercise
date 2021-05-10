package com.exercise.auth.config.exception;

public class AccountAlreadyExistsException extends RuntimeException {

    public AccountAlreadyExistsException() {
        super();
    }

    public AccountAlreadyExistsException(final String message) {
        super(message);
    }

}
