package com.karafra.bitchutedl.downloader;

import java.net.URL;

import com.karafra.bitchutedl.exceptions.FileDownloadException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

/**
 * Simple downloader for files on the internet.
 * 
 * @since 1.0
 * @version 1.0
 * @author Karafra
 */
@Service
public class SimpleMp4Downloader extends AbstractDownloader {

    /**
     * {@inheritDoc}
     */
    @Override
    public Resource downloadAsResource(URL url) {
        Resource resource = new UrlResource(url);
        if (resource.exists()) {
            return resource;
        }
        throw new FileDownloadException(
                String.format("Such file does not exist (%s)", url.toString()));
    }
}
