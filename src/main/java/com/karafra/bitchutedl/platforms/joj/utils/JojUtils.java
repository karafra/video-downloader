package com.karafra.bitchutedl.platforms.joj.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utilities for downloading videos from JOJ video archive.
 * 
 * @version 1.0
 * 
 * @author Karafra
 */
public final class JojUtils {

    /**
     * Checks if link is joj archive compatible. Example link matching this validator:
     * https://videoportal.joj.sk/mafstory/epizoda/2080-vendeta
     * 
     * @param link link to validate
     * @return true if link is from youtube video archive.
     */
    public static boolean isJojLink(String link) {
        Pattern pattern = Pattern.compile(
                "https?:\\/\\/videoportal.joj.sk\\/([a-zA-Z0-9]{1,64})\\/epizoda\\/.{1,64}\\/",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(link);
        return matcher.matches();
    }
}
