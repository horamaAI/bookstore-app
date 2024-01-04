package com.bookstore.app.bookstoreapp.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public class CustomException extends RuntimeException {
    private final List<String> errors;

    private final HttpStatus status;

    public CustomException(String errorMessage) {
        super(errorMessage);
        this.errors = Collections.singletonList(errorMessage);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public CustomException(String errorMessage, HttpStatus status) {
        super(errorMessage);
        this.status = status;
        this.errors = Collections.singletonList(errorMessage);

    }

    public CustomException(List<String> errorMessages, HttpStatus status) {
        this.status = status;
        this.errors = errorMessages;
    }

    public CustomException(String errorMessage, Throwable cause, HttpStatus status) {
        super(errorMessage, cause);
        this.status = status;
        errors = Collections.singletonList(errorMessage);
    }

    public CustomException(Throwable cause, HttpStatus status) {
        super(cause);
        this.status = status;
        errors = null;
    }

}
