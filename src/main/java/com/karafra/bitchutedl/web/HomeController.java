package com.karafra.bitchutedl.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.karafra.bitchutedl.platforms.bitchute.BitchuteService;
import com.karafra.bitchutedl.platforms.bitchute.dtos.DownloadPageProps;
import com.karafra.bitchutedl.platforms.bitchute.dtos.IndexPageForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private BitchuteService bitchuteService;

    /**
     * Page you see when you open website.
     * 
     * @since 1.0
     * 
     * @category endpoint
     * 
     * @return
     */
    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    /**
     * Page containing information about download.
     * 
     * @param form form passed from index page.
     * 
     * @return page containing information about download.
     * 
     * @since 1.0
     * 
     * @category endpoint
     */
    @PostMapping("/download")
    public ModelAndView download(@ModelAttribute() IndexPageForm form) {
        DownloadPageProps props = bitchuteService.processVideo(form.getUrl());
        ModelAndView modelAndView = new ModelAndView("download-page.html");
        modelAndView.addObject("props", props);
        return modelAndView;
    }

    /**
     * Endpoint that redirects to index page if wrong method is sent to /download endpoint.
     * 
     * Needs to enhanced.
     * 
     * @param response response to redirect.
     * 
     * @throws IOException when redirect dows not exits
     * 
     *         {@inheritDoc}
     * 
     * @since 1.0
     * 
     * @category endpoint
     */
    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        response.sendRedirect("/");
    }
}
