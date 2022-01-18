package com.karafra.bitchutedl.exceptions.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.karafra.bitchutedl.exceptions.ApiRuntimeException;
import com.karafra.bitchutedl.exceptions.handlers.ApiRuntimeExceptionHandler.JsonErrorResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ApiRuntimeExceptionHandlerTests {

    @Mock
    private ServletWebRequest mockServletWebRequest;

    private ApiRuntimeExceptionHandler handler;

    @BeforeEach
    private void setup() {
        handler = new ApiRuntimeExceptionHandler();
    }

    @Test
    @Tag("basic")
    @DisplayName("Should handle error")
    public void shouldHandleError() {
        // Given
        String testExceptionMessage = "Test message";
        ApiRuntimeException ex = new ApiRuntimeException(testExceptionMessage);
        // When
        ResponseEntity<JsonErrorResponse> response =
                handler.handleRuntimeException(ex, mockServletWebRequest);
        // Then
        assertEquals(testExceptionMessage, response.getBody().getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should handle error with custom code")
    public void shouldHandleErrorWithCustomCode() {
        // Given
        String testExceptionMessage = "Test message";
        ApiRuntimeException ex =
                new ApiRuntimeException(testExceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        // When
        ResponseEntity<JsonErrorResponse> response =
                handler.handleRuntimeException(ex, mockServletWebRequest);
        // Then
        assertEquals(testExceptionMessage, response.getBody().getMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
