package com.bookstore.app.bookstoreapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)

public class InvalidInputException extends CustomException {
    public InvalidInputException(String errorMessage, HttpStatus status) {
        super(errorMessage, status);
    }

    public InvalidInputException(List<String> errorMessages, HttpStatus status) {
        super(errorMessages, status);
    }

    public InvalidInputException(String errorMessage, Throwable cause, HttpStatus status) {
        super(errorMessage, cause, status);
    }

    public InvalidInputException(Throwable cause, HttpStatus status) {
        super(cause, status);
    }
}
