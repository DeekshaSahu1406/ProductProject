package com.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AppGlobalExceptionHandler {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    @ExceptionHandler(AppExceptionBase.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

    public ResponseEntity<String> handleAppException(AppExceptionBase ex) {
        // You can customize the response format as needed
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }

}
