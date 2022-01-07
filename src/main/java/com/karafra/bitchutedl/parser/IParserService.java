package com.karafra.bitchutedl.parser;

import com.karafra.bitchutedl.parser.DTOs.ParsedLinkResponseDTO;

/**
 * Interface defining methods for parsing bitchute video.
 * 
 * @author Karafra
 */
public interface IParserService {
  /**
   * Parses video link from bitchute to return raw url to video stream.
   * @param link link to bitchute video.
   * @return link to raw stream.
   */
  ParsedLinkResponseDTO processBitchuteVideo(String link);
}
