package com.karafra.bitchutedl.platforms.bitchute.dtos;

import org.springframework.core.io.Resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Simple wrapper containing properties of download .
 * 
 * @version 1.0
 * 
 * @since 1.0
 * 
 * @author Karafra.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleDownload {
    /**
     * Size of download
     */
    private long sizeInBytes;
    private Resource resource;
}
