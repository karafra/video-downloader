package com.karafra.bitchutedl.exceptions;

/**
 * Exception thrown when something goes wrong during processing download.
 * 
 * @since 1.0
 * @version 1.0
 * @category exception
 * @author Karafra
 */
public class FileDownloadException extends ApiRuntimeException {
  /**
   * Constructor used when only message is provided.
   * 
   * @param message
   */
  public FileDownloadException(String message) {
    super(message);
  }
}
