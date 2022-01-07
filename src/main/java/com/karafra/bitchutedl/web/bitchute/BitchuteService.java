package com.karafra.bitchutedl.web.bitchute;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.karafra.bitchutedl.downloader.AbstractDownloader;
import com.karafra.bitchutedl.exceptions.FileDownloadException;
import com.karafra.bitchutedl.exceptions.NotValidLinkException;
import com.karafra.bitchutedl.extractor.IExtractor;
import com.karafra.bitchutedl.web.IDownloaderService;
import com.karafra.bitchutedl.web.bitchute.DTOs.ParsedLinkResponseDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Class handling parsing videos from Bitchute.
 * 
 * @author Karafra
 */
@Service
public class BitchuteService implements IDownloaderService {

    /**
     * Logger service.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(BitchuteService.class);

    /**
     * Extractor for bitchute videos.
     */
    @Autowired
    private IExtractor bitchuteExtractor;

    /**
     * General downloader service.
     */
    @Autowired
    private AbstractDownloader simpleMp4Downloader;

    /**
     * {@inheritDoc}
     */
    public ParsedLinkResponseDTO processVideo(String id) {
        String url = String.format("http://www.bitchute.com/video/%s/", id);
        LOGGER.info("Processing bitchute video", url);
        try {
            return new ParsedLinkResponseDTO(bitchuteExtractor.extract(url), url);
        } catch (IOException ex) {
            throw new NotValidLinkException(url);
        }
    }
    /**
     * {@inheritDoc}
     */
    public ResponseEntity<Resource> downloadVideo(String urlString) {
        try {
            URL url = new URL(urlString);
            LOGGER.info("Downloading file from URL {}", url);
            return simpleMp4Downloader.downloadAsResponseEntity(url);
        } catch (MalformedURLException ex) {
            LOGGER.error("Url {} is malformed", urlString);
            throw new FileDownloadException(
                    String.format("Link {} is malformed", urlString)
            );
        }
    }
}
