package com.product.exception;

import org.springframework.http.HttpStatus;

public class AppExceptionBase extends Exception {
    private HttpStatus status;
    private String errorReason;

    public AppExceptionBase(String message){
        super(message);
    }

    public AppExceptionBase(String message, HttpStatus status, String errorReason){
        super(message);
        this.status = status;
        this.errorReason = errorReason;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }


}
