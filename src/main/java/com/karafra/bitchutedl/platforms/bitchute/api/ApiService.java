package com.karafra.bitchutedl.platforms.bitchute.api;

import java.net.MalformedURLException;
import java.net.URL;

import com.karafra.bitchutedl.downloader.AbstractDownloader;
import com.karafra.bitchutedl.exceptions.FileDownloadException;
import com.karafra.bitchutedl.exceptions.NotValidLinkException;
import com.karafra.bitchutedl.platforms.bitchute.dtos.DownloadVideoRequest;
import com.karafra.bitchutedl.platforms.bitchute.dtos.ParsedLinkResponse;
import com.karafra.bitchutedl.platforms.bitchute.parser.BitchuteParser;
import com.karafra.bitchutedl.platforms.bitchute.utils.BitchuteUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller handling Bitchute API
 * 
 * @author Karafra
 * 
 * @category service
 * 
 * @version 1.0
 * 
 * @since 1.0
 * 
 * @see <a href="http://www.bitchute.com/">Bitchute</a>
 */
@Service
public class ApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiService.class);

    @Autowired
    private AbstractDownloader simpleMp4Downloader;

    /**
     * Bitchute extractor service.
     */
    @Autowired
    private BitchuteParser bitchuteExtractor;

    /**
     * Extracts link to bitchute video from link.
     * 
     * @param link link to extract raw video form.
     * 
     * @return response containing both original and raw link.
     * 
     * 
     * @since 1.0
     * 
     * @category logic
     */
    public ParsedLinkResponse getTarget(String link) {
        if (!BitchuteUtils.isBitchuteLink(link)) {
            throw new NotValidLinkException(link);
        }
        ParsedLinkResponse response = new ParsedLinkResponse();
        response.setLinkOriginal(link);
        response.setLinkRaw(bitchuteExtractor.getTarget(link));
        return response;
    }

    /**
     * Parses link to bitchute video from id of video.
     * 
     * @param id of video to extract raw link from.
     * 
     * @return response containing both original and raw link.
     * 
     * @since 1.0
     * 
     * @category logic
     */
    public ParsedLinkResponse getTargetFromId(String id) {
        return getTarget(BitchuteUtils.idToLink(id));
    }

    /**
     * Logic for viewing videos on this website.
     * 
     * @param id id of video.
     * 
     * @return view and model of website for viewing videos,
     * 
     * @since 1.0
     * 
     * @category logic
     */
    public ModelAndView viewVideoById(String id) {
        ParsedLinkResponse response = getTargetFromId(id);
        ModelAndView modelAndView = new ModelAndView("view-video.html");
        modelAndView.addObject("videoLink", response.getLinkRaw());
        return modelAndView;
    }

    /**
     * Logic for downloading videos.
     * 
     * @param id identifier of video.
     * 
     * @return downloadable resource.
     * 
     * @since 1.0
     * 
     * @category logic
     */
    public ResponseEntity<Resource> downloadVideo(String id) {
        return downloadVideo(new DownloadVideoRequest(BitchuteUtils.idToLink(id)));
    }

    /**
     * Logic for downloading video from link.
     * 
     * @param req request containing url to download from.
     * 
     * @return downloadable resource.
     * 
     * @since 1.0
     * 
     * @category logic
     */
    public ResponseEntity<Resource> downloadVideo(DownloadVideoRequest req) {
        if (!BitchuteUtils.isBitchuteLink(req.getLinkToVideo())) {
            throw new NotValidLinkException(req.getLinkToVideo());
        }
        try {
            URL url = new URL(bitchuteExtractor.getTarget(req.getLinkToVideo()));
            LOGGER.info("Downloading file from URL {}", url);
            return simpleMp4Downloader.downloadAsResponseEntity(url);
        } catch (MalformedURLException ex) {
            LOGGER.error("Url {} is malformed", req.getLinkToVideo());
            throw new FileDownloadException(
                    String.format("Link %s is malformed", req.getLinkToVideo()));
        }
    }
}
