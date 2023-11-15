package com.product.Enum;

public enum ErrorCode {

    ERR_INVALID_REQUEST("INVALID_REQUEST"),
    ERR_PROCESSING("ERROR_IN_APPLICATION"),
    ERR_DB("DB_ERROR"),
    NOT_ALLOWED("NOT_ALLOWED"),
    ERR_UNKNOWN("UNKNOWN_REASON");

    private String errorCodeValue;

    ErrorCode(String errorCodeValue){
        this.errorCodeValue = errorCodeValue;
    }

    @Override
    public String toString() {
        return errorCodeValue;
    }
}
