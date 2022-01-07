package com.karafra.bitchutedl.exceptions;

/**
 * Exception thrown provided link does not point to video.
 * 
 * @author Karafra
 */
public class NotValidLinkException extends ApiRuntimeException {
  public NotValidLinkException(String link) {
    super(String.format("%s is not valid link", link));
  }
}
