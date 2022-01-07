package com.karafra.bitchutedl.extractor;

import java.io.IOException;

/**
 * Interface providing all necessary methods for extractor.
 * 
 * @author Karafra.
 */
public interface IExtractor {
    /**
     * Extracts raw link from given link
     * 
     * @param linkToVideo
     *            link from which raw is to be extracted
     * 
     * @return raw link.
     */
    String extract(String linkToVideo) throws IOException;
}
