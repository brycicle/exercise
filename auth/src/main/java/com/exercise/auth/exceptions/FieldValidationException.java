package com.exercise.auth.exceptions;

import lombok.Data;

@Data
public class FieldValidationException extends RuntimeException {
    private final String field;
    private final String message;

    public FieldValidationException(final String field, final String message) {
        super(message);
        this.field = field;
        this.message = message;
    }

}
