package com.karafra.bitchutedl.platforms.bitchute.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Response sent when link is parsed successfully.
 * 
 * @author Karafra
 * 
 * @version 1.0
 * 
 * @since 1.0
 * 
 * @category dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParsedLinkResponse {
    /**
     * Raw ink to bitchute CDN.
     */
    private String linkRaw;
    /**
     * Original link to video.
     */
    private String linkOriginal;
}
