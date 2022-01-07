package com.karafra.bitchutedl.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * Base class for api exceptions.
 * 
 * @author Karafra
 */
public class ApiRuntimeException extends RuntimeException {

  /**
   * Http status to be returned when exception is thrown.
   */
  @Getter
  private HttpStatus status = HttpStatus.BAD_REQUEST;

  /**
   * Default empty constructor, will return response code 400.
   * 
   * Status code will be {@link org.springframework.http.HttpStatus#BAD_REQUEST
   * 400}.
   */
  public ApiRuntimeException() {
    super();
  }

  /**
   * Constructor used when status code is provided.
   * 
   * @param status status code to be returned.
   */
  public ApiRuntimeException(HttpStatus status) {
    super();
    this.status = status;
  }

  /**
   * Default string only constructor, will return response code 400.
   * 
   * Status code will be {@link org.springframework.http.HttpStatus#BAD_REQUEST
   * 400}.
   * 
   * @param message simple string describing exception.
   */
  public ApiRuntimeException(String message) {
    super(message);
  }

  /**
   * Constructor used when message and status is provided.
   *
   * @param message description of exception.
   * @param status  status to be returned.
   */
  public ApiRuntimeException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  /**
   * Constructor used when message and cause is provided.
   * 
   * Status code will be {@link org.springframework.http.HttpStatus#BAD_REQUEST
   * 400}.
   * 
   * @param message description of exception.
   * @param cause   case of exception.
   */
  public ApiRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructor used when message and cause is provided.
   * 
   * @param message description of exception.
   * @param cause   case of exception.
   * @param status  status to be returned.
   */
  public ApiRuntimeException(String message, Throwable cause, HttpStatus status) {
    super(message, cause);
    this.status = status;
  }

  /**
   * Constructor used when only cause is provided.
   * 
   * Status code will be {@link org.springframework.http.HttpStatus#BAD_REQUEST
   * 400}.
   * 
   * @param cause cause of exception.
   */
  public ApiRuntimeException(java.lang.Throwable cause) {
    super(cause);
  }

  /**
   * Constructor used when cause and status is provided.
   * 
   * @param cause  cause of error.
   * @param status status code to be returned.
   */
  public ApiRuntimeException(java.lang.Throwable cause, HttpStatus status) {
    super(cause);
    this.status = status;
  }
}
