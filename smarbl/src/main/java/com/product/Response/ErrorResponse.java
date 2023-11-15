package com.product.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse extends BaseResponse{
    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("errorReason")
    private String errorReason;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "errorCode='" + errorCode + '\'' +
                ", errorReason='" + errorReason + '\'' +
                '}';
    }
}
