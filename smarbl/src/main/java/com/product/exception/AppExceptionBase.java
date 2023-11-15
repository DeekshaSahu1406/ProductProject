package com.product.exception;

import com.product.Enum.ErrorCode;
import org.springframework.http.HttpStatus;

public class AppExceptionBase extends Exception {
    private HttpStatus status;
    private String errorReason;
    private ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

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

    @Override
    public String toString() {
        return "AppExceptionBase{" +
                "status=" + status +
                ", errorReason='" + errorReason + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }
}
