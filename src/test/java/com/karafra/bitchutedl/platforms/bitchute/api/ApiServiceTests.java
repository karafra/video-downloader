package com.karafra.bitchutedl.platforms.bitchute.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.net.URL;

import com.karafra.bitchutedl.downloader.SimpleMp4Downloader;
import com.karafra.bitchutedl.exceptions.ApiRuntimeException;
import com.karafra.bitchutedl.exceptions.FileDownloadException;
import com.karafra.bitchutedl.exceptions.NotValidLinkException;
import com.karafra.bitchutedl.platforms.bitchute.dtos.DownloadVideoRequest;
import com.karafra.bitchutedl.platforms.bitchute.dtos.ParsedLinkResponse;
import com.karafra.bitchutedl.platforms.bitchute.parser.BitchuteParser;

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
public class ApiServiceTests {

    @Autowired
    private ResourceLoader resourceLoader;

    @Mock
    private SimpleMp4Downloader mockSimpleMp4Downloader;

    @Mock
    private BitchuteParser mockBitchuteParser;

    @InjectMocks
    private ApiService service;

    private static final String NOT_VALID_BITCHUTE_LINK = "not-valid-link";
    private static final String VALID_BITCHUTE_LINK =
            "https://www.bitchute.com/video/1jPPRUHK9nII/";
    private static final String TARGET =
            "https://seed151.bitchute.com/IDo4as4O5ylC/1jPPRUHK9nII.mp4";
    private static final String VALID_ID = "1jPPRUHK9nII";

    @Test
    @Tag("basic")
    @DisplayName("Should throw error if getTarget on not bitchute link")
    public void shouldThrowErrorIfGetTargetOnNotBitchuteLink() {
        // Given
        String expMessage = new NotValidLinkException(NOT_VALID_BITCHUTE_LINK).getMessage();
        // When
        NotValidLinkException ex = assertThrows(NotValidLinkException.class,
                () -> service.getTarget(NOT_VALID_BITCHUTE_LINK));
        // Then
        assertEquals(expMessage, ex.getMessage());
        verifyNoInteractions(mockBitchuteParser);
        verifyNoInteractions(mockSimpleMp4Downloader);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should get target of video if valid link")
    public void shouldGetTarget() {
        // Given
        when(mockBitchuteParser.getTarget(VALID_BITCHUTE_LINK)).thenReturn(TARGET);
        // When
        ParsedLinkResponse response = service.getTarget(VALID_BITCHUTE_LINK);
        // Then
        verify(mockBitchuteParser).getTarget(VALID_BITCHUTE_LINK);
        assertEquals(VALID_BITCHUTE_LINK, response.getLinkOriginal());
        assertEquals(TARGET, response.getLinkRaw());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should rethrow error if that occurs when fetching target from id")
    public void shouldRethrowErrorIfFetchingTargetById() {
        // Given
        String errMessage = "test";
        when(mockBitchuteParser.getTarget(VALID_BITCHUTE_LINK))
                .thenThrow(new ApiRuntimeException(errMessage));
        // When
        ApiRuntimeException ex =
                assertThrows(ApiRuntimeException.class, () -> service.getTargetFromId(VALID_ID));
        // Then
        assertEquals(errMessage, ex.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should get target of video if valid link from id")
    public void shouldGetTargetFromId() {
        // Given
        when(mockBitchuteParser.getTarget(VALID_BITCHUTE_LINK)).thenReturn(TARGET);
        // When
        ParsedLinkResponse response = service.getTargetFromId(VALID_ID);
        // Then
        verify(mockBitchuteParser).getTarget(VALID_BITCHUTE_LINK);
        assertEquals(VALID_BITCHUTE_LINK, response.getLinkOriginal());
        assertEquals(TARGET, response.getLinkRaw());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should enable to view video")
    public void shouldViewVideo() {
        // Given
        when(mockBitchuteParser.getTarget(VALID_BITCHUTE_LINK)).thenReturn(TARGET);
        // When
        ModelAndView modelAndView = service.viewVideoById(VALID_ID);
        // Then
        assertEquals("view-video.html", modelAndView.getViewName());
        assertEquals(TARGET, modelAndView.getModel().get("videoLink"));
    }

    @Test
    @Tag("basic")
    @DisplayName("Should download video form link")
    public void shouldGetVideoFromLink() {
        // Given
        DownloadVideoRequest downloadVideoRequest = new DownloadVideoRequest();
        downloadVideoRequest.setLinkToVideo(VALID_BITCHUTE_LINK);
        ResponseEntity<Resource> responseEntity =
                ResponseEntity.ok().body(resourceLoader.getResource("classpath:data/sample.mp4"));
        when(mockBitchuteParser.getTarget(VALID_BITCHUTE_LINK)).thenReturn(TARGET);
        when(mockSimpleMp4Downloader.downloadAsResponseEntity(any(URL.class)))
                .thenReturn(responseEntity);
        // When
        ResponseEntity<Resource> response = service.downloadVideo(downloadVideoRequest);
        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseEntity.getBody(), response.getBody());
        verify(mockBitchuteParser).getTarget(VALID_BITCHUTE_LINK);
        verify(mockSimpleMp4Downloader).downloadAsResponseEntity(any());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should throw error when URL is malformed when downloading video form link")
    public void shouldThrowErrorWhenMalformedUlrGetVideoFromLink() {
        // Given
        DownloadVideoRequest downloadVideoRequest = new DownloadVideoRequest();
        downloadVideoRequest.setLinkToVideo(VALID_BITCHUTE_LINK);
        when(mockBitchuteParser.getTarget(VALID_BITCHUTE_LINK)).thenReturn("TARGET");
        // When
        FileDownloadException exception = assertThrows(FileDownloadException.class,
                () -> service.downloadVideo(downloadVideoRequest));
        // Then
        verifyNoInteractions(mockSimpleMp4Downloader);
        verify(mockBitchuteParser).getTarget(anyString());
        assertEquals("Link https://www.bitchute.com/video/1jPPRUHK9nII/ is malformed",
                exception.getMessage());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should throw error when URL is not from bitchute video")
    public void shouldThrowErrorWhenDownloadExceptionGetVideoFromLink()
            throws MalformedURLException {
        // Given
        NotValidLinkException exceptionToThrow = new NotValidLinkException(NOT_VALID_BITCHUTE_LINK);
        // When
        DownloadVideoRequest downloadVideoRequest = new DownloadVideoRequest();
        downloadVideoRequest.setLinkToVideo(NOT_VALID_BITCHUTE_LINK);
        NotValidLinkException ex = assertThrows(NotValidLinkException.class,
                () -> service.downloadVideo(downloadVideoRequest));
        // Then
        verifyNoInteractions(mockBitchuteParser);
        verifyNoInteractions(mockSimpleMp4Downloader);
        assertEquals(exceptionToThrow.getMessage(), ex.getMessage());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should download video form link")
    public void shouldGetVideoFromId() {
        // Given
        DownloadVideoRequest downloadVideoRequest = new DownloadVideoRequest();
        downloadVideoRequest.setLinkToVideo(VALID_BITCHUTE_LINK);
        ResponseEntity<Resource> responseEntity =
                ResponseEntity.ok().body(resourceLoader.getResource("classpath:data/sample.mp4"));
        when(mockBitchuteParser.getTarget(VALID_BITCHUTE_LINK)).thenReturn(TARGET);
        when(mockSimpleMp4Downloader.downloadAsResponseEntity(any(URL.class)))
                .thenReturn(responseEntity);
        // When
        ResponseEntity<Resource> response = service.downloadVideo(VALID_ID);
        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseEntity.getBody(), response.getBody());
        verify(mockBitchuteParser).getTarget(VALID_BITCHUTE_LINK);
        verify(mockSimpleMp4Downloader).downloadAsResponseEntity(any());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should throw error when URL is malformed when downloading video form ID")
    public void shouldThrowErrorWhenMalformedUlrGetVideoFromID() {
        // Given
        DownloadVideoRequest downloadVideoRequest = new DownloadVideoRequest();
        downloadVideoRequest.setLinkToVideo(VALID_BITCHUTE_LINK);
        when(mockBitchuteParser.getTarget(VALID_BITCHUTE_LINK)).thenReturn("TARGET");
        // When
        FileDownloadException exception =
                assertThrows(FileDownloadException.class, () -> service.downloadVideo(VALID_ID));
        // Then
        verifyNoInteractions(mockSimpleMp4Downloader);
        verify(mockBitchuteParser).getTarget(anyString());
        assertEquals("Link https://www.bitchute.com/video/1jPPRUHK9nII/ is malformed",
                exception.getMessage());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should throw error when URL is not from bitchute video when geting by id")
    public void shouldThrowErrorWhenDownloadExceptionGetVideoFromID() throws MalformedURLException {
        // Given
        NotValidLinkException exceptionToThrow =
                new NotValidLinkException("https://www.bitchute.com/video/??a-4?/");
        // When
        DownloadVideoRequest downloadVideoRequest = new DownloadVideoRequest();
        downloadVideoRequest.setLinkToVideo(NOT_VALID_BITCHUTE_LINK);
        NotValidLinkException ex =
                assertThrows(NotValidLinkException.class, () -> service.downloadVideo("??a-4?"));
        // Then
        verifyNoInteractions(mockBitchuteParser);
        verifyNoInteractions(mockSimpleMp4Downloader);
        assertEquals(exceptionToThrow.getMessage(), ex.getMessage());
    }

}
