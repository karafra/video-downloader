package com.karafra.bitchutedl.platforms.bitchute.parser;

import java.io.IOException;

import com.karafra.bitchutedl.exceptions.NotValidLinkException;
import com.karafra.bitchutedl.parser.AbstractSimpleParser;
import com.karafra.bitchutedl.platforms.bitchute.dtos.Download;
import com.karafra.bitchutedl.platforms.bitchute.dtos.DownloadPageProps;
import com.karafra.bitchutedl.platforms.bitchute.utils.BitchuteUtils;

import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Extractor for Bitchute.
 * 
 * @see <a href="http://bitchute.com">Bitchute</a>
 * 
 * @author Karafra
 * 
 * @version 1.0
 * 
 * @since 1.0
 * 
 * @category service
 */
@Service
public class BitchuteParser extends AbstractSimpleParser {

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
     * 
     * @since 1.0
     * 
     * @category parserFunction
     */
    @Override
    public DownloadPageProps extract(String linkToVideo) {
        if (!BitchuteUtils.isBitchuteLink(linkToVideo)) {
            throw new NotValidLinkException(linkToVideo);
        }
        get(linkToVideo);
        String target = getTarget();
        DownloadPageProps props = new DownloadPageProps();
        Download dto = Download.builder().format(getFormat(target))
                .link(BitchuteUtils.generateDownloadLink(linkToVideo)).build();
        props.addDownload(dto);
        props.setAuthor(getPublisher());
        props.setDescription(getTitle());
        props.setThumbnailLink(getThumbnailLink());
        props.setPreviewLink(BitchuteUtils.generateViewLink(linkToVideo));
        props.setQrDownLink(BitchuteUtils.generateDownloadLink(linkToVideo));
        return props;
    }

    /**
     * Gets target of video player.
     * 
     * @return target of video player.
     * 
     * @since 1.0
     * 
     * @category parserFunction
     */
    protected String getTarget() {
        Element element = getElementByXpath("//*[@id=\"player\"]/source");
        return element.attr("src");
    }

    /**
     * Gets target of video player.
     * 
     * @param link to process
     * 
     * @return target of video player.
     * 
     * @since 1.0
     * 
     * @category parserFunction
     */
    public String getTarget(String link) {
        get(link);
        return getTarget();
    }

    /**
     * Gets title of video
     * 
     * @param link to process
     * 
     * @return title of video.
     * 
     * @since 1.0
     * 
     * @category parserFunction
     */
    public String getTitle(String link) {
        get(link);
        return getTitle();
    }

    /**
     * Gets title of video
     * 
     * @return title of video.
     * 
     * @since 1.0
     * 
     * @category parserFunction
     */
    protected String getTitle() {
        Element element = getElementByXpath("//*[@id=\"video-title\"]");
        return element.text();
    }

    /**
     * Gets date video was published.
     * 
     * @param link to process.
     * 
     * @return date the video was published.
     * 
     * @since 1.0
     * 
     * @category parserFunction
     */
    public String getDate(String link) {
        get(link);
        return getDate();
    }

    /**
     * Gets date video was published.
     * 
     * @return date the video was published.
     * 
     * @since 1.0
     * 
     * @category parserFunction
     */
    protected String getDate() {
        Element element =
                getElementByXpath("//*[@id=\"video-watch\"]/div/div[1]/div[3]/div/div[1]");
        return element.text();
    }

    /**
     * Gets publisher of video.
     * 
     * @param link link to process
     * 
     * @return publisher of video.
     * 
     * @since 1.0
     * 
     * @category parserFunction
     */
    public String getPublisher(String link) {
        get(link);
        return getPublisher();
    }

    /**
     * Gets publisher of video.
     * 
     * @return publisher of video.
     * 
     * @since 1.0
     * 
     * @category parserFunction
     */
    protected String getPublisher() {
        Element element = getElementByXpath(
                "//*[@id=\"video-watch\"]/div/div[1]/div[3]/div/div[2]/div[3]/p[1]/a");
        return element.text();
    }

    /**
     * Gets format of video from raw url.
     * 
     * @param rawLink raw link CDN.
     * 
     * @return format of video.
     * 
     * @since 1.0
     * 
     * @category parserFunction
     */
    protected String getFormat(String rawLink) {
        String[] linkSplit = rawLink.split("\\.");
        return linkSplit[linkSplit.length - 1];
    }

    /**
     * Get thumbnail of video.
     * 
     * @return gets thumbnail of video.
     * 
     * @since 1.0
     * 
     * @category parserFunction
     */
    protected String getThumbnailLink() {
        Element element = getElementByXpath("//*[@id=\"player\"]");
        return element.attr("poster");
    }

    /**
     * Get thumbnail of video.
     * 
     * @param link to process.
     * 
     * @return gets thumbnail of video.
     * 
     * @since 1.0
     * 
     * @category parserFunction
     */
    public String getThumbnailLink(String link) {
        get(link);
        return getThumbnailLink();
    }
}
