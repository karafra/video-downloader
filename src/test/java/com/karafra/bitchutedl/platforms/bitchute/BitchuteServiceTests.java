package com.karafra.bitchutedl.platforms.bitchute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import com.karafra.bitchutedl.downloader.SimpleMp4Downloader;
import com.karafra.bitchutedl.exceptions.FileDownloadException;
import com.karafra.bitchutedl.exceptions.NotValidLinkException;
import com.karafra.bitchutedl.platforms.bitchute.dtos.Download;
import com.karafra.bitchutedl.platforms.bitchute.dtos.DownloadPageProps;
import com.karafra.bitchutedl.platforms.bitchute.parser.BitchuteParser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class BitchuteServiceTests {

    @Mock
    private SimpleMp4Downloader mockSimpleMp4Downloader;
    @Mock
    private BitchuteParser mockBitchuteParser;
    /**
     * Tested component.
     */
    @InjectMocks
    private BitchuteService service;

    // Constants
    private static final String NOT_VALID_URL = "not-valid-url";
    private static final String MOCK_BITCHUTE_LINK = "http://www.bictchute.com/video/randomId";
    private static final String MOCK_DOWN_URL = "http://mock.string.url";
    private static final String MOCK_FORMAT = "format";
    private static final String MOCK_DURATION = "duration";
    private static final String MOCK_THUMBNAIL_LINK = "http://mock.thumbnail/";
    private static final String MOCK_AUTHOR = "author";
    private static final String MOCK_DESCRIPTION = "description";
    private static final String MOCK_QR_DOWN_LINK = "http://mock.qr/download";
    private static final String MOCK_PREVIEW_LINK = "http://preview.video/";
    private static final String MOCK_SIZE = "mockSize";
    private static final Download MOCK_DOWNLOAD =
            Download.builder().format(MOCK_FORMAT).link(MOCK_DOWN_URL).size(MOCK_SIZE).build();
    private DownloadPageProps MOCK_DOWNLOAD_PAGE_PROPS =
            DownloadPageProps.builder().duration(MOCK_DURATION).thumbnailLink(MOCK_THUMBNAIL_LINK)
                    .author(MOCK_AUTHOR).description(MOCK_DESCRIPTION).qrDownLink(MOCK_QR_DOWN_LINK)
                    .previewLink(MOCK_PREVIEW_LINK).build();
    private static final Resource MOCK_RESOURCE = mock(Resource.class);
    private static final ResponseCookie cookie = ResponseCookie.from("downloadStarted", "false")
            .maxAge(24 * 60 * 60).sameSite("None").path("/download").build();
    private static final ResponseEntity<Resource> MOCK_RESPONSE_ENTITY_RESOURCE =
            ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + "filename.mp4" + "\"")
                    .header(HttpHeaders.SET_COOKIE, cookie.toString()).body(MOCK_RESOURCE);

    @BeforeEach
    private void setup() {
        MOCK_DOWNLOAD_PAGE_PROPS.setDownloads(new ArrayList<Download>());
        MOCK_DOWNLOAD_PAGE_PROPS.addDownload(MOCK_DOWNLOAD);
    }

    @Test
    @Tag("basic")
    @DisplayName("Context should load")
    public void contextLoads() {
        // Given
        // When
        // Then
        assertNotNull(service);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should extract raw url")
    public void shouldExtractUrl() throws IOException {
        // Given
        when(mockBitchuteParser.extract(MOCK_BITCHUTE_LINK)).thenReturn(MOCK_DOWNLOAD_PAGE_PROPS);
        // When
        DownloadPageProps props = service.processVideo(MOCK_BITCHUTE_LINK);
        // Then
        verify(mockBitchuteParser).extract(MOCK_BITCHUTE_LINK);
        verifyNoInteractions(mockSimpleMp4Downloader);
        assertEquals(MOCK_DOWNLOAD_PAGE_PROPS, props);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should rethrow error that occurs during conversion of string to URL object")
    public void shouldRethrowErrorWhenConverting() {
        // Given
        when(mockBitchuteParser.extract(anyString()))
                .thenThrow(new NotValidLinkException(NOT_VALID_URL));
        // When
        NotValidLinkException ex = assertThrows(NotValidLinkException.class,
                () -> mockBitchuteParser.extract(NOT_VALID_URL));
        // Then
        verify(mockBitchuteParser).extract(NOT_VALID_URL);
        verifyNoInteractions(mockSimpleMp4Downloader);
        assertEquals("not-valid-url is not valid link", ex.getMessage());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should download video form url")
    public void shouldDownloadVideoFromLink() {
        // Given
        when(mockSimpleMp4Downloader.downloadAsResponseEntity(any()))
                .thenReturn(MOCK_RESPONSE_ENTITY_RESOURCE);
        // When
        ResponseEntity<Resource> response = service.downloadVideo(MOCK_BITCHUTE_LINK);
        // Then
        verify(mockSimpleMp4Downloader).downloadAsResponseEntity(any());
        assertEquals(MOCK_RESPONSE_ENTITY_RESOURCE, response);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should throw exception if malformed url")
    public void shouldThrowExceptionWhenMalformedUrl() {
        // Given
        // When
        FileDownloadException ex = assertThrows(FileDownloadException.class,
                () -> service.downloadVideo(NOT_VALID_URL));
        // Then
        verifyNoInteractions(mockSimpleMp4Downloader);
        assertEquals(String.format("Link {} is malformed", NOT_VALID_URL), ex.getMessage());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should ignore other exceptions")
    public void shouldIgnoreOtherExceptions() {
        // Given
        when(mockSimpleMp4Downloader.downloadAsResponseEntity(any()))
                .thenThrow(new RuntimeException("Test exception"));
        // When
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.downloadVideo(MOCK_BITCHUTE_LINK));
        // Then
        verify(mockSimpleMp4Downloader).downloadAsResponseEntity(any());
        assertEquals("Test exception", ex.getMessage());
    }
}
