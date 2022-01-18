package com.karafra.bitchutedl.platforms.bitchute.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Props class used for transfer to download page template.
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Download {
    /**
     * Quality of video.
     */
    @Builder.Default
    private String quality = "-";
    /**
     * Format of video
     */
    private String format;
    /**
     * Link to video (raw)
     */
    private String link;
    /**
     * Size of video
     */
    private String size;
}
