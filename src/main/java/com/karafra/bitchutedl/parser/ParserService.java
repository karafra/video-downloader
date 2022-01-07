package com.karafra.bitchutedl.parser;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.karafra.bitchutedl.exceptions.NotValidLinkException;
import com.karafra.bitchutedl.extractor.IExtractor;
import com.karafra.bitchutedl.parser.DTOs.ParsedLinkResponseDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class handling parsing videos from Bitchute.
 * 
 * @author Karafra
 */
@Service
public class ParserService implements IParserService {

  /**
   * Logger service.
   */
  private final static Logger LOGGER = LoggerFactory.getLogger(ParserService.class);

  /**
   * Extractor for bitchute videos.
   */
  @Autowired
  private IExtractor bitchuteExtractor;

  /**
   * {@inheritDoc}
   */
  public ParsedLinkResponseDTO processBitchuteVideo(String link) {
    if (!isValidLinkToVideo(link)) {
      throw new NotValidLinkException(link);
    }
    try {
      return new ParsedLinkResponseDTO(bitchuteExtractor.extract(link), link);
    } catch (IOException ex) {
      throw new NotValidLinkException(link);
    }
  }


  /**
   * Checks if provided by user is valid bitchute url.
   * 
   * @param link link to check.
   */
  private Boolean isValidLinkToVideo(String link) {
      LOGGER.info("Validating link {}", link);
      Pattern pattern = Pattern.compile("(https?):\\/\\/(?:www)?.?bitchute.com/video/([a-zA-Z0-9]{1,64})/");
      Matcher matcher = pattern.matcher(link);
      if (!matcher.matches()) {
        LOGGER.error("{} is not bitchute url", link);
        return false;
      }
      LOGGER.info("{} url is valid", link);
      return true;
  }
}
