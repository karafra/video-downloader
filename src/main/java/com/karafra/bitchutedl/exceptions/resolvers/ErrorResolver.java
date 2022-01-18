package com.karafra.bitchutedl.exceptions.resolvers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@Component
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class ErrorResolver implements ErrorViewResolver {

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status,
            Map<String, Object> model) {
        if (status.value() >= 500) {
            return new ModelAndView("error/5xx.html");
        } else if (status == HttpStatus.NOT_FOUND || status == HttpStatus.METHOD_NOT_ALLOWED) {
            return new ModelAndView("error/404.html");
        }
        return null;
    }
}
