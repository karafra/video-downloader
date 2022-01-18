package com.karafra.bitchutedl.processing;

import com.karafra.bitchutedl.platforms.bitchute.dtos.DownloadPageProps;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

/**
 * Interface defining methods for parsing bitchute video.
 * 
 * @author Karafra
 */
public interface IProcessingService {
    /**
     * Parses video link from bitchute to return raw url to video stream.
     * 
     * @param link link to bitchute video.
     * 
     * @return link to raw stream.
     */
    DownloadPageProps processVideo(String link);

    /**
     * Downloads video from bitchute.
     * 
     * @param urlString urllike string.
     * 
     * @return downloadable resource.
     */
    ResponseEntity<Resource> downloadVideo(String urlString);
}
