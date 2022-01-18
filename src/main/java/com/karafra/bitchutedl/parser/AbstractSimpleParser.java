package com.karafra.bitchutedl.parser;

import java.io.IOException;
import java.util.Objects;


import com.karafra.bitchutedl.exceptions.NotValidLinkException;
import com.karafra.bitchutedl.exceptions.WrongXPathException;
import com.karafra.bitchutedl.platforms.bitchute.dtos.DownloadPageProps;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.seimicrawler.xpath.JXDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Generated;
import lombok.Getter;

/**
 * Interface providing all necessary methods for extractor.
 * 
 * @author Karafra.
 */
public abstract class AbstractSimpleParser {

    /**
     * Logging service.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSimpleParser.class);

    /**
     * Connection to url.
     */
    @Getter
    @Generated
    private JXDocument document;

    /**
     * Extracts raw link from given link
     * 
     * @param linkToVideo link from which raw is to be extracted
     * 
     * @return raw link.
     */
    public abstract DownloadPageProps extract(String linkToVideo) throws IOException;

    /**
     * Connects to URL, used to avoid duplicate calls.
     * 
     * @param urlString url to connect to
     * 
     * @return Established connection.
     */
    public JXDocument get(String urlString) {
        Connection connection = Jsoup.connect(urlString);
        try {
            this.document = JXDocument.create(connection.get());
            return this.document;
        } catch (IOException ex) {
            LOGGER.error("Cannot connect to {}. Error({})", urlString, ex.getMessage());
            throw new NotValidLinkException(urlString);
        }
    }

    /**
     * Gets element by xpath and and throws
     * {@link com.karafra.bitchutedl.exceptions.WrongXPathException exception} if xpath does not
     * exist in DOM.
     * 
     * 
     * @param xpath xpath to search for.
     * 
     * @return element with given xpath.
     */
    public Element getElementByXpath(String xpath) {
        Element element = getDocument().selNOne(xpath).asElement();
        if (Objects.isNull(element)) {
            LOGGER.error("Error occurred while fetching target from url ({})", "Not found");
            throw new WrongXPathException(xpath);
        }
        return element;
    }
}
