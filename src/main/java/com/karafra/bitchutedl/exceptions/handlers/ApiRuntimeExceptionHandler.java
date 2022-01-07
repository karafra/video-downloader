package com.karafra.bitchutedl.exceptions.handlers;

import com.karafra.bitchutedl.exceptions.ApiRuntimeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Global exception handler for API, returning JSON instead of plain text.
 * This {@link org.springframework.web.bind.annotation.ControllerAdvice advice}
 * can be overwritten at controller level as it has lowest
 * {@link org.springframework.core.Ordered#LOWEST_PRECEDENCE precedence}.
 * 
 * @author Karafra
 */
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class ApiRuntimeExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(ApiRuntimeExceptionHandler.class);

  /**
   * Private class representing runtime exception, will not be used anywhere else.
   * 
   * @author Karafra
   */
  @Data
  @NoArgsConstructor
  private class JsonResponse {
    private String message;
    private int httpStatus;
  }

  /**
   * Method that handles runtime exceptions.
   * 
   * @param ex  exception to be handled
   * @param req {@link org.springframework.web.context.request.ServletWebRequest
   *            request} during processing of which RE occurred
   * @author Karafra
   */
  @ExceptionHandler(ApiRuntimeException.class)
  public ResponseEntity<JsonResponse> handleRuntimeException(ApiRuntimeException ex, ServletWebRequest req) {
    LOGGER.error(String.format("Error occurred in {}", req.getRequest().getRequestURI()));
    JsonResponse jsonResponse = new JsonResponse();
    jsonResponse.httpStatus = ex.getStatus().value();
    jsonResponse.setMessage(ex.getMessage());
    return new ResponseEntity<JsonResponse>(jsonResponse, HttpStatus.BAD_REQUEST);
  }
}
