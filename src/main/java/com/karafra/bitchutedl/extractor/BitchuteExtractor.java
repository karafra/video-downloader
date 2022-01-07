package com.karafra.bitchutedl.extractor;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Extractor for Bitchute
 * 
 * @see <a href="http://bitchute.com">Bitchute</a>
 * 
 * @author Karafra
 */
@Service
public class BitchuteExtractor implements IExtractor {

    /**
     * Logging service.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BitchuteExtractor.class);

    /**
     * User agent to use.
     */
    @Value("${webclient.userAgent:Mozilla}")
    private String userAgent;

    /**
     * Timeout of request.
     */
    @Value("${webclient.timeout:5000}")
    private int timeout;

    /**
     * Referrer of request.
     */
    @Value("${webclient.referrer:http://google.com}")
    private String referrer;

    /**
     * Extracts raw to video from bitchute link.
     * 
     * {@inheritDoc}
     */
    @Override
    public String extract(String linkToVideo) throws IOException {
        Connection connection = Jsoup.connect(linkToVideo);
        connection.timeout(timeout);
        connection.referrer(referrer);
        try {
            LOGGER.info("Extracting link from URL {}", linkToVideo);
            Document document = connection.get();
            // Get video player
            Element player = document.getElementById("player");
            // Get video source link
            String videoSource = player.getElementsByTag("source").attr("src");
            return videoSource;
        } catch (IOException ex) {
            LOGGER.error("Error occurred while extracting video source ({})", ex.getMessage());
            throw ex;
        }
    }

}
