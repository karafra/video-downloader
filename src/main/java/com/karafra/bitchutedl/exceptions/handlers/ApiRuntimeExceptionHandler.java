package com.karafra.bitchutedl.exceptions.handlers;

import com.karafra.bitchutedl.exceptions.ApiRuntimeException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * Global exception handler for API, returning JSON instead of plain text. This
 * {@link org.springframework.web.bind.annotation.ControllerAdvice advice} can be overwritten at
 * controller level as it has lowest {@link org.springframework.core.Ordered#LOWEST_PRECEDENCE
 * precedence}.
 * 
 * @since 1.0
 * @version 1.0
 * @author Karafra
 */
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class ApiRuntimeExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Private class representing runtime exception, will not be used anywhere else.
     * 
     * @author Karafra
     * 
     * @version 1.0
     * 
     * @since 1.0
     */
    @Data
    @Generated
    @NoArgsConstructor
    public class JsonErrorResponse {
        /**
         * Message describing exception.
         */
        private String message;
        /**
         * Status code to return.
         */
        private int httpStatus;
    }

    /**
     * Method that handles runtime exceptions.
     * 
     * @param ex exception to be handled
     * @param req {@link org.springframework.web.context.request.ServletWebRequest request} during
     *        processing of which RE occurred
     * 
     * @since 1.0
     * 
     * 
     */
    @ExceptionHandler(ApiRuntimeException.class)
    public ResponseEntity<JsonErrorResponse> handleRuntimeException(ApiRuntimeException ex,
            ServletWebRequest req) {
        JsonErrorResponse jsonResponse = new JsonErrorResponse();
        jsonResponse.setHttpStatus(ex.getStatus().value());
        jsonResponse.setMessage(ex.getMessage());
        return new ResponseEntity<JsonErrorResponse>(jsonResponse, ex.getStatus());
    }
}
