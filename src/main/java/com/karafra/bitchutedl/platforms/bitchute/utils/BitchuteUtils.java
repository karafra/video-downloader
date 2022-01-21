package com.karafra.bitchutedl.platforms.bitchute.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.karafra.bitchutedl.exceptions.NotValidLinkException;

/**
 * Class encapsulating all utilities related to bitchute.
 * 
 * @author Karafra
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public final class BitchuteUtils {

    private BitchuteUtils() {}

    /**
     * Checks if link provided is valid bitchute video link.
     * 
     * @param link link to check.
     * 
     * @return true if link is valid, false otherwise.
     * 
     * @since 1.0
     */
    public static boolean isBitchuteLink(String link) {
        Pattern pattern = Pattern.compile(
                "https?:\\/\\/(?:www)?\\.?bitchute.com\\/video\\/([a-zA-Z0-9]{1,64})\\/",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(link);
        return matcher.matches();
    }

    /**
     * Converts bitchute video id to link to video.
     * 
     * @param id id of video.
     * 
     * @return link to video.
     * 
     * @since 1.0
     */
    public static String idToLink(String id) {
        return String.format("https://www.bitchute.com/video/%s/", id);
    }

    /**
     * Converts link to id.
     * 
     * @param link link to video.
     * 
     * @return download link to video.
     * 
     * @since 1.0
     */
    public static String generateDownloadLink(String link) {
        if (!isBitchuteLink(link)) {
            throw new NotValidLinkException(link);
        }
        String[] linkSplit = link.split("/");
        return String.format("http://localhost:8080/bitchute/%s/download",
                linkSplit[linkSplit.length - 1]);
    }

    /**
     * Converts link to id.
     * 
     * @param link link to video.
     * 
     * @return download link to video.
     * 
     * @since 1.0
     */
    public static String generateViewLink(String link) {
        if (!isBitchuteLink(link)) {
            throw new NotValidLinkException(link);
        }
        String[] linkSplit = link.split("/");
        return String.format("http://localhost:8080/bitchute/%s/view",
                linkSplit[linkSplit.length - 1]);
    }

}
