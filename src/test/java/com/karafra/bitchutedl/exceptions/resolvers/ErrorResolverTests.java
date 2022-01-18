package com.karafra.bitchutedl.exceptions.resolvers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

@SpringBootTest
public class ErrorResolverTests {
    /**
     * Tested component.
     */
    @Autowired
    private ErrorResolver errorResolver;

    @Test
    @Tag("basic")
    @DisplayName("Should return name 5xx.html page if 5xx response code")
    public void shouldReturn5xxTemplate() throws Exception {
        // Given
        // When
        ModelAndView responseTemplate =
                errorResolver.resolveErrorView(null, HttpStatus.INTERNAL_SERVER_ERROR, null);
        // Then
        assertEquals(responseTemplate.getViewName(), "error/5xx.html");
    }

    @Test
    @Tag("basic")
    @DisplayName("Should return name 404.html page if 404 response code")
    public void shouldReturn404TemplateOnNotFound() throws Exception {
        // Given
        // When
        ModelAndView responseTemplate =
                errorResolver.resolveErrorView(null, HttpStatus.NOT_FOUND, null);
        // Then
        assertEquals(responseTemplate.getViewName(), "error/404.html");
    }


    @Test
    @Tag("basic")
    @DisplayName("Should return name 404.html page if 403 response code")
    public void shouldReturn404TemplateOnNotAllowed() throws Exception {
        // Given
        // When
        ModelAndView responseTemplate =
                errorResolver.resolveErrorView(null, HttpStatus.METHOD_NOT_ALLOWED, null);
        // Then
        assertEquals(responseTemplate.getViewName(), "error/404.html");
    }

    @Test
    @Tag("basic")
    @DisplayName("Should return null if not handled response code")
    public void shouldReturnNullIfNotHandledResponseCode() throws Exception {
        // Given
        // When
        ModelAndView responseTemplate =
                errorResolver.resolveErrorView(null, HttpStatus.NOT_ACCEPTABLE, null);
        // Then
        assertNull(responseTemplate);
    }
}
