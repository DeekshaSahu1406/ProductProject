package com.product.exception;

import com.product.Enum.ErrorCode;
import com.product.Response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppGlobalExceptionHandler {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    @ExceptionHandler({ValidationException.class,AppExceptionBase.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

    public final ResponseEntity<ErrorResponse> handleAppException(Exception ex, WebRequest webRequest) {
        // You can customize the response format as needed
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse() ;
        if (ex instanceof ValidationException) {
            ValidationException validationException = (ValidationException) ex;
            if (validationException.getStatus() != null) {
                status = validationException.getStatus();
            }
            errorResponse.setMessage(ex.getMessage());
            errorResponse.setErrorReason(validationException.getErrorReason().toString());
            errorResponse.setErrorCode(ErrorCode.ERR_INVALID_REQUEST.toString());
            errorResponse.setErrorReason(validationException.getStatus().toString());
            errorResponse.setMessage("Server processing exception during request");
            errorResponse.setStatus(status.toString());
            errorResponse.setErrorReason(ex.getMessage());
        }

        return  new ResponseEntity<>(errorResponse,status);
    }

}
