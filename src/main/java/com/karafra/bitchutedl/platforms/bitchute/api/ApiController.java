package com.karafra.bitchutedl.platforms.bitchute.api;

import java.util.Objects;

import com.karafra.bitchutedl.exceptions.ArgumentRequiredException;
import com.karafra.bitchutedl.platforms.bitchute.dtos.DownloadVideoRequest;
import com.karafra.bitchutedl.platforms.bitchute.dtos.ParseLinkRequest;
import com.karafra.bitchutedl.platforms.bitchute.dtos.ParsedLinkResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller handling Bitchute downloads
 * 
 * @author Karafra
 * 
 * @version 1.0
 * 
 * @since 1.0
 * 
 * 
 * @see <a href="http://www.bitchute.com/">Bitchute</a>
 */
@RestController
@RequestMapping("/bitchute")
public class ApiController {

    @Autowired
    private ApiService bitchuteApiService;

    /**
     * Endpoint used for parsing videos from bitchute.
     * 
     * @param id link to video,
     * 
     * @return response containing both original and raw link.
     * 
     * @since 1.0
     * 
     */
    @GetMapping("/{video}/parse")
    public ParsedLinkResponse parseVideo(@PathVariable("video") String id) {
        return bitchuteApiService.getTargetFromId(id);
    }

    /**
     * Parses link to raw video from bitchute link.
     * 
     * @param parseLinkRequest request.
     * 
     * @return response containing both original and raw link
     * 
     * @since 1.0
     * 
     */
    @PostMapping("/parse")
    public ParsedLinkResponse parseVideo(@RequestParam ParseLinkRequest parseLinkRequest) {
        if (!Objects.isNull(parseLinkRequest.getOriginalLink())
                && !parseLinkRequest.getOriginalLink().isBlank()) {
            return bitchuteApiService.getTarget(parseLinkRequest.getOriginalLink());
        } else if (!Objects.isNull(parseLinkRequest.getId())
                && !parseLinkRequest.getId().isBlank()) {
            return bitchuteApiService.getTargetFromId(parseLinkRequest.getId());
        }
        throw new ArgumentRequiredException("originalLink", String.class);
    }

    /**
     * Endpoint used for viewing videos on bitchute on this website.
     * 
     * @param id identifier of video on bitchute.
     * 
     * @return rendered video template.
     * 
     * @since 1.0
     * 
     */
    @GetMapping("{video}/view")
    public ModelAndView viewVideo(@PathVariable("video") String id) {
        return bitchuteApiService.viewVideoById(id);
    }

    /**
     * Endpoint used for downloading videos from bitchute.
     * 
     * @param id id of video on bitchute.
     * 
     * @return downloadable stream.
     * 
     * @since 1.0
     * 
     */
    @GetMapping("{video}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable("video") String id) {
        return bitchuteApiService.downloadVideo(id);
    }

    /**
     * Downloads video from request.
     * 
     * @param req request containing link to download video from.
     * 
     * @return downloadable resource
     * 
     * @since 1.0
     * 
     */
    @PostMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam DownloadVideoRequest req) {
        return bitchuteApiService.downloadVideo(req);
    }
}
