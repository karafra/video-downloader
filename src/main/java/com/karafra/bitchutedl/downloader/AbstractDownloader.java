package com.karafra.bitchutedl.downloader;

import java.net.URL;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

/**
 * Abstract class defining all common methods for simple file download from
 * internet.
 * 
 * @author Karafra
 * @category service
 * @since 1.0
 * @version 1.0
 */
public abstract class AbstractDownloader {
  /**
   * Generates random UUID.
   * 
   * @return random UUID
   */
  protected static String generateIdentifier() {
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  }

  /**
   * 
   * @param extension
   * 
   * @return
   */
  protected static String buildFilename(String extension) {
    String filename = String.format("%s.%s", generateIdentifier(), extension);
    return filename;
  }

  /**
   * Downloads file from url.
   * 
   * @param url
   *            url to download file from.
   * 
   * @return resource stream providing bytes for saving.
   */
  public abstract Resource downloadAsResource(URL url);

  /**
   * Returns file from url as response entity.
   * 
   * @param url url to download from.
   * @return downloadable response entity.
   */
  public ResponseEntity<Resource> downloadAsResponseEntity(URL url) {
    Resource resource = downloadAsResource(url);
    return ResponseEntity
        .ok()
        .header(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + buildFilename("mp4")
                + "\"")
        .body(resource);
  }
}
