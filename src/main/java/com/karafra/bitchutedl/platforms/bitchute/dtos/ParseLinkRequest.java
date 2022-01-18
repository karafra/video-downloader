package com.karafra.bitchutedl.platforms.bitchute.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Dto for parsing raw links to resources from bitchute videos.
 * 
 * @author Karafra
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParseLinkRequest {
    /**
     * Original link to video to parse.
     */
    private String originalLink;
    /**
     * Id of video to parse.
     */
    private String Id;
}
