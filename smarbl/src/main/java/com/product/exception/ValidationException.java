package com.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ValidationException extends AppExceptionBase {

    public ValidationException(String message, String reason){
        super(message, HttpStatus.NOT_FOUND, reason);
    }

}
