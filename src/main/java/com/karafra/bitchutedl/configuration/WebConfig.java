package com.karafra.bitchutedl.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


/**
 * Template engine configuration.
 * 
 * {@link https://www.thymeleaf.org/} 
 * @author Karafra
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
  
  @Value("${thymeleaf.template.path:templates/}")
  private String templatePrefix;
  
  @Bean
  @Description("Thymeleaf template resolver (HTML5)")
  public ClassLoaderTemplateResolver classLoaderTemplateResolver() {
    ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver();
    classLoaderTemplateResolver.setPrefix(templatePrefix);
    classLoaderTemplateResolver.setCacheable(false);
    classLoaderTemplateResolver.setSuffix(".html");
    classLoaderTemplateResolver.setTemplateMode("HTML5");
    classLoaderTemplateResolver.setCharacterEncoding("UTF-8");
    return classLoaderTemplateResolver;
  }

  @Bean
  @Description("Thymeleaf template engine")
  public SpringTemplateEngine springTemplateEngine() {
    SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
    springTemplateEngine.setTemplateResolver(classLoaderTemplateResolver());
    return springTemplateEngine;
  }

  @Bean
  @Description("Thymeleaf view resolver")
  public ViewResolver viewResolver() {
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setTemplateEngine(springTemplateEngine());
    viewResolver.setCharacterEncoding("UTF-8");
    return viewResolver;
  }
}
