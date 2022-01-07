package com.karafra.bitchutedl.web.bitchute;

import com.karafra.bitchutedl.web.IDownloaderService;
import com.karafra.bitchutedl.web.bitchute.DTOs.ParsedLinkResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller handling Bitchute downloads
 * 
 * @author Karafra
 * 
 * @category Controller
 * 
 * @version 1.0
 * 
 * @see <a href="http://www.bitchute.com/">Bitchute</a>
 */
@RestController
@RequestMapping("/bitchute")
public class ParserController {

    /**
     * Parser service.
     */
    @Autowired
    private IDownloaderService bitchuteService;

    /**
     * Endpoint used for parsing videos from bitchute.
     * 
     * @param id
     *           link to video
     * 
     * @return response containing both original and raw link
     */
    @GetMapping("/{video}/parse")
    public ParsedLinkResponseDTO parseVideo(@PathVariable("video") String id) {
        return bitchuteService.processVideo(id);
    }

    /**
     * Endpoint used for viewing videos on bitchute on this website.
     * 
     * @param id
     *           identifier of video on bitchute.
     * 
     * @return rendered video template.
     */
    @GetMapping("{video}/view")
    public ModelAndView viewVideo(@PathVariable("video") String id) {
        ParsedLinkResponseDTO dto = parseVideo(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("videoLink", dto.getLinkRaw());
        modelAndView.setViewName("video-to-download.html");
        return modelAndView;
    }

    /**
     * Endpoint used for downloading videos from bitchute.
     * 
     * @param id id of video on bitchute.
     * @return downloadable stream.
     */
    @GetMapping("{video}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable("video") String id) {
        ParsedLinkResponseDTO parsedLinkResponseDTO = parseVideo(id);
        return bitchuteService.downloadVideo(parsedLinkResponseDTO.getLinkRaw());
    }
}
