package com.product.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends AppExceptionBase {

    public ValidationException(String message, String reason){
        super(message, HttpStatus.BAD_REQUEST, reason);
    }

}
