package com.product.Response;

import org.springframework.http.HttpStatus;

public class ProductResponsemethods {

    public static GenericResponse createResponse(String message, Object data) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setMessage(message);
        genericResponse.setStatus(HttpStatus.OK.toString());
        genericResponse.setData(data);
        return genericResponse;
    }


    public static GenericResponse createErrorResponse(String errorMessage, String reason) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setMessage(errorMessage);
        genericResponse.setStatus(HttpStatus.BAD_REQUEST.toString());
        genericResponse.setErrorMessage(reason);
        return genericResponse;
    }
}
