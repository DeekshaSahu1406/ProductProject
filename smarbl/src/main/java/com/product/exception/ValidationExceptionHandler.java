package com.product.exception;

import com.product.Response.GenericResponse;
import org.springframework.http.HttpStatus;

public class ValidationExceptionHandler {
    public GenericResponse handleValidationException(ValidationException ve) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setMessage("Validation Error");
        genericResponse.setStatus(HttpStatus.BAD_REQUEST.toString());
        genericResponse.setErrorMessage(ve.getErrorReason()); // Assuming you have a getReason() method in ValidationException

        return genericResponse;
    }

    public GenericResponse handleGenericException(Exception e) {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setMessage("Internal Server Error");
        genericResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        genericResponse.setErrorMessage(e.getMessage());

        return genericResponse;
    }

}
