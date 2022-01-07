package com.karafra.bitchutedl.parser;

import java.util.Objects;

import com.karafra.bitchutedl.exceptions.ArgumentRequiredException;
import com.karafra.bitchutedl.parser.DTOs.ParsedLinkResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/parser")
public class ParserController {

  @Autowired
  private IParserService parserService;

  @GetMapping
  public ParsedLinkResponseDTO parseVideo(@RequestParam(name = "video", required = false) String link) {
    if (Objects.isNull(link) || link.isEmpty()) {
      throw new ArgumentRequiredException("link", String.class);
    }
    return parserService.processBitchuteVideo(link);
  }

  @GetMapping("/view")
  public ModelAndView viewVideo(@RequestParam(name = "video", required = false) String link) {
    ParsedLinkResponseDTO dto = parseVideo(link);
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("videoLink", dto.getLinkRaw());
    modelAndView.setViewName("video-to-download.html");
    return modelAndView;
  }
}
