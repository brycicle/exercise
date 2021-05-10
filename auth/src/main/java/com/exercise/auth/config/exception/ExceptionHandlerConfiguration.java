package com.exercise.auth.config.exception;

import com.exercise.auth.exceptions.FieldValidationException;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.util.concurrent.ConcurrentHashMap;

@RestControllerAdvice
@Log
public final class ExceptionHandlerConfiguration {
    public static final String ERROR_MSG = "Error";
    public static final String FIELD_MSG = "Field";

    @ExceptionHandler(FieldValidationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ConcurrentHashMap<String, String> customException(
            final FieldValidationException exception, final WebRequest request
    ) {
        log(exception, request);

        final ConcurrentHashMap<String, String> response = new ConcurrentHashMap<>();
        response.put(FIELD_MSG, exception.getField());
        response.put(ERROR_MSG, exception.getMessage());
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ConcurrentHashMap<String, String> globalException(final Exception exception, final WebRequest request) {
        log(exception, request);

        final ConcurrentHashMap<String, String> response = new ConcurrentHashMap<>();
        response.put(ERROR_MSG, exception.getMessage());
        exception.printStackTrace();
        return response;
    }

    public void log(final Exception exception, final WebRequest request) {
        log.warning(request.toString());
        log.warning(exception.getMessage());
    }
}
