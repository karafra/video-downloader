package com.karafra.bitchutedl.platforms.bitchute.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.karafra.bitchutedl.exceptions.ApiRuntimeException;
import com.karafra.bitchutedl.exceptions.ArgumentRequiredException;
import com.karafra.bitchutedl.platforms.bitchute.dtos.DownloadVideoRequest;
import com.karafra.bitchutedl.platforms.bitchute.dtos.ParseLinkRequest;
import com.karafra.bitchutedl.platforms.bitchute.dtos.ParsedLinkResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

@SpringBootTest
public class ApiControllerTests {

    @Autowired
    private ResourceLoader resourceLoader;

    @Mock
    private ApiService mockApiService;

    @InjectMocks
    private ApiController controller;

    @Test
    @Tag("basic")
    @DisplayName("Should parse link to video")
    public void shouldParseVideo() {
        // Given
        String originalLink = "originalLink";
        String rawLink = "rawLink";
        ParsedLinkResponse response = new ParsedLinkResponse();
        response.setLinkOriginal(originalLink);
        response.setLinkRaw(rawLink);
        when(mockApiService.getTargetFromId(anyString())).thenReturn(response);
        // When
        ParsedLinkResponse responseFromController = controller.parseVideo("video");
        // Then
        verify(mockApiService).getTargetFromId(anyString());
        assertEquals(response, responseFromController);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should rethrow error if link is not valid")
    public void shouldRethrowErrorWhenLinkCantBeParsed() {
        // Given
        String exMsg = "Test error";
        when(mockApiService.getTargetFromId(anyString())).thenThrow(new ApiRuntimeException(exMsg));
        String originalLink = "originalLink";
        String rawLink = "rawLink";
        ParsedLinkResponse response = new ParsedLinkResponse();
        response.setLinkOriginal(originalLink);
        response.setLinkRaw(rawLink);
        // When
        ApiRuntimeException ex = assertThrows(ApiRuntimeException.class,
                () -> controller.parseVideo("parseLinkRequest"));
        // Then
        assertEquals(exMsg, ex.getMessage());
        verify(mockApiService).getTargetFromId(anyString());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should parse video from request body if only originalLink")
    public void shouldParseVideoPost() {
        // Given
        ParseLinkRequest request = new ParseLinkRequest();
        request.setOriginalLink("original link");
        ParsedLinkResponse response = new ParsedLinkResponse();
        response.setLinkOriginal("originalLink");
        response.setLinkRaw("rawLink");
        when(mockApiService.getTarget(anyString())).thenReturn(response);
        // When
        ParsedLinkResponse responseFromController = controller.parseVideo(request);
        // Then
        verify(mockApiService).getTarget(anyString());
        verify(mockApiService, times(0)).getTargetFromId(anyString());
        assertEquals(response, responseFromController);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should rethrow error from request body if only originalLink is present")
    public void shouldRethrowErrorIfOnlyOriginalLinkIsPresent() {
        // Given
        ParseLinkRequest request = new ParseLinkRequest();
        request.setOriginalLink("original link");
        String exMsg = "Exception message";
        when(mockApiService.getTarget(any())).thenThrow(new ApiRuntimeException(exMsg));
        // When
        ApiRuntimeException ex =
                assertThrows(ApiRuntimeException.class, () -> controller.parseVideo(request));
        // Then
        assertEquals(exMsg, ex.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        verify(mockApiService).getTarget(anyString());
        verify(mockApiService, times(0)).getTargetFromId(anyString());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should parse video from request body if only Id")
    public void shouldParseVideoPostIfOnlyId() {
        // Given
        ParseLinkRequest request = new ParseLinkRequest();
        request.setId("id");
        ParsedLinkResponse response = new ParsedLinkResponse();
        response.setLinkOriginal("originalLink");
        response.setLinkRaw("rawLink");
        when(mockApiService.getTargetFromId(anyString())).thenReturn(response);
        // When
        ParsedLinkResponse responseFromController = controller.parseVideo(request);
        // Then
        verify(mockApiService).getTargetFromId(anyString());
        verify(mockApiService, times(0)).getTarget(anyString());
        assertEquals(response, responseFromController);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should rethrow error from request body if only id is present")
    public void shouldRethrowErrorIfOnlyOriginalIdIsPresent() {
        // Given
        ParseLinkRequest request = new ParseLinkRequest();
        request.setId("id");
        String exMsg = "Exception message";
        when(mockApiService.getTargetFromId(anyString())).thenThrow(new ApiRuntimeException(exMsg));
        // When
        ApiRuntimeException ex =
                assertThrows(ApiRuntimeException.class, () -> controller.parseVideo(request));
        // Then
        assertEquals(exMsg, ex.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        verify(mockApiService).getTargetFromId(anyString());
        verify(mockApiService, times(0)).getTarget(anyString());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should throw exception when parsing video and none of required arguments are present")
    public void shouldThrowErrorWhenParsingAndBothArgumentsAreMissing() {
        // Given
        ParseLinkRequest request = new ParseLinkRequest();
        // When
        ArgumentRequiredException ex =
                assertThrows(ArgumentRequiredException.class, () -> controller.parseVideo(request));
        // Then
        assertEquals(new ArgumentRequiredException("originalLink", String.class).getMessage(),
                ex.getMessage());
        verifyNoInteractions(mockApiService);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should throw exception when parsing video and none of required arguments are blank")
    public void shouldThrowErrorWhenParsingAndBothArgumentsAreBlank() {
        // Given
        ParseLinkRequest request = new ParseLinkRequest();
        request.setId("    ");
        request.setOriginalLink("\t\n");
        // When
        ArgumentRequiredException ex =
                assertThrows(ArgumentRequiredException.class, () -> controller.parseVideo(request));
        // Then
        assertEquals(new ArgumentRequiredException("originalLink", String.class).getMessage(),
                ex.getMessage());
        verifyNoInteractions(mockApiService);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should view video")
    public void shouldViewVideo() {
        // Given
        String rawLink = "rawLink";
        ModelAndView modelAndView = new ModelAndView("view-video.html");
        modelAndView.addObject("videoLink", rawLink);
        when(mockApiService.viewVideoById(anyString())).thenReturn(modelAndView);
        // When
        ModelAndView view = controller.viewVideo("video");
        // Then
        verify(mockApiService).viewVideoById(anyString());
        assertEquals("view-video.html", view.getViewName());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should download video")
    public void shouldDownloadVideo() {
        // Given
        Resource resource = resourceLoader.getResource("classpath:data/sample.mp4");
        ResponseEntity<Resource> responseEntity = ResponseEntity.ok().body(resource);
        when(mockApiService.downloadVideo(anyString())).thenReturn(responseEntity);
        // When
        ResponseEntity<Resource> responseFromController = controller.downloadFile("video");
        // Then
        verify(mockApiService).downloadVideo(anyString());
        assertEquals(responseEntity, responseFromController);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should rethrow error that occurs while downloading video")
    public void shouldRethrowErrorWhenVideoCantBeDownloaded() {
        // Given
        String exMsg = "Test error";
        when(mockApiService.downloadVideo(anyString())).thenThrow(new ApiRuntimeException(exMsg));
        // When
        ApiRuntimeException ex = assertThrows(ApiRuntimeException.class,
                () -> controller.downloadFile("parseLinkRequest"));
        // Then
        assertEquals(exMsg, ex.getMessage());
        verify(mockApiService).downloadVideo(anyString());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should download video form request")
    public void shouldDownloadVideoFromRequest() {
        // Given
        DownloadVideoRequest request = new DownloadVideoRequest();
        request.setLinkToVideo("linkToVideo");
        Resource resource = resourceLoader.getResource("classpath:data/sample.mp4");
        ResponseEntity<Resource> responseEntity = ResponseEntity.ok().body(resource);
        when(mockApiService.downloadVideo(request)).thenReturn(responseEntity);
        // When
        ResponseEntity<Resource> responseFromController = controller.downloadFile(request);
        // Then
        verify(mockApiService).downloadVideo(request);
        assertEquals(responseEntity, responseFromController);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should rethrow error that occurs when downloading video form request")
    public void shouldRethrowErrorWhenVideoCantBeDownloadedFromRequest() {
        // Given
        String exMsg = "Test error";
        when(mockApiService.downloadVideo(anyString())).thenThrow(new ApiRuntimeException(exMsg));
        // When
        ApiRuntimeException ex = assertThrows(ApiRuntimeException.class,
                () -> controller.downloadFile("parseLinkRequest"));
        // Then
        assertEquals(exMsg, ex.getMessage());
        verify(mockApiService).downloadVideo(anyString());
    }
}
