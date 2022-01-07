package com.karafra.bitchutedl.web;


import com.karafra.bitchutedl.web.bitchute.DTOs.ParsedLinkResponseDTO;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

/**
 * Interface defining methods for parsing bitchute video.
 * 
 * @author Karafra
 */
public interface IDownloaderService {
    /**
     * Parses video link from bitchute to return raw url to video stream.
     * 
     * @param link
     *             link to bitchute video.
     * 
     * @return link to raw stream.
     */
    ParsedLinkResponseDTO processVideo(String link);

    /**
     * Downloads video from bitchute.
     * 
     * @param urlString urllike string.
     * @return downloadable resource.
     */
     ResponseEntity<Resource> downloadVideo(String urlString);
}
