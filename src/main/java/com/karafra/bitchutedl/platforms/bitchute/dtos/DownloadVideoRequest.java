package com.karafra.bitchutedl.platforms.bitchute.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request object to download video.
 * 
 * @author Karafra
 * 
 * @since 1.0
 * 
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownloadVideoRequest {
    /**
     * Link to video to download.
     */
    private String linkToVideo;
}
