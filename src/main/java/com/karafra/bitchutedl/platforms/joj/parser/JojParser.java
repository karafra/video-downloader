package com.karafra.bitchutedl.platforms.joj.parser;

import java.io.IOException;

import com.karafra.bitchutedl.exceptions.NotValidLinkException;
import com.karafra.bitchutedl.parser.AbstractSimpleParser;
import com.karafra.bitchutedl.platforms.bitchute.dtos.DownloadPageProps;
import com.karafra.bitchutedl.platforms.joj.utils.JojUtils;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

/**
 * Class handling all extracting links to CDN from video links.
 * 
 * @author Karafra
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@Service
public class JojParser extends AbstractSimpleParser {

    /**
     * {@inheritDoc}
     */
    @Override
    public DownloadPageProps extract(String linkToVideo) throws IOException {
        if (!JojUtils.isJojLink(linkToVideo)) {
            throw new NotValidLinkException(linkToVideo);
        }

        return null;
    }


    public String getLinkToEmbed(String link) {
        get(link);
        return getLinkToEmbed();
    }

    public String getLinkToEmbed() {
        Element element = getElementByXpath(
                "//iframe[contains(@class, \"kframe b-player-container js-player-container\")]");
        return element.attr("src");
    }
}
