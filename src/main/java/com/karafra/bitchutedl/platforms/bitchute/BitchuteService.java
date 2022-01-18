package com.karafra.bitchutedl.platforms.bitchute;

import java.net.MalformedURLException;
import java.net.URL;

import com.karafra.bitchutedl.downloader.AbstractDownloader;
import com.karafra.bitchutedl.exceptions.FileDownloadException;
import com.karafra.bitchutedl.platforms.bitchute.dtos.DownloadPageProps;
import com.karafra.bitchutedl.platforms.bitchute.parser.BitchuteParser;
import com.karafra.bitchutedl.processing.IProcessingService;

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
public class BitchuteService implements IProcessingService {

    /**
     * Logger service.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(BitchuteService.class);

    /**
     * Extractor for bitchute videos.
     */
    @Autowired
    private BitchuteParser bitchuteParser;

    /**
     * General downloader service.
     */
    @Autowired
    private AbstractDownloader simpleMp4Downloader;

    /**
     * {@inheritDoc}
     */
    public DownloadPageProps processVideo(String url) {
        LOGGER.info("Processing bitchute video", url);
        return bitchuteParser.extract(url);
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
            throw new FileDownloadException(String.format("Link {} is malformed", urlString));
        }
    }

}
