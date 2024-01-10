package com.bookstore.app.bookstoreapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends CustomException {

    public BadRequestException(String errorMessage) {
        super(errorMessage);
        System.out.println("Bad exception message: " + errorMessage);
    }

    public BadRequestException(String errorMessage, HttpStatus status) {
        super(errorMessage, status);
        System.out.println("Bad exception message: " + errorMessage);
    }

}
