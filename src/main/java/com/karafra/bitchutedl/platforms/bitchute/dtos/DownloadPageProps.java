package com.karafra.bitchutedl.platforms.bitchute.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Props class used for transfer to download page template.
 * 
 * @author Karafra
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@Data
@AllArgsConstructor
@Builder
public class DownloadPageProps {
    /**
     * List of all downloads.
     */
    private List<Download> downloads;
    /**
     * Length of video.
     */
    private String duration;
    /**
     * 
     */
    private String thumbnailLink;

    /**
     * Name of video.
     */
    private String author;
    /**
     * Description of download.
     */
    private String description;
    /**
     * Download code for QR code.
     */
    private String qrDownLink;

    /**
     * Link used in preview.
     */
    private String previewLink;

    /**
     * Adds download to download list.
     * 
     * @param download download object to add.
     * 
     * @return download objet that was added.
     */
    public Download addDownload(Download download) {
        downloads.add(download);
        return download;
    }

    /**
     * No args constructor.
     */
    public DownloadPageProps() {
        downloads = new ArrayList<Download>();
    }
}
