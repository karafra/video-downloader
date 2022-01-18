package com.karafra.bitchutedl.downloader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.net.URL;

import com.karafra.bitchutedl.exceptions.FileDownloadException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;

@SpringBootTest
public class SimpleMp4DownloaderTests {

    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * Tested component.
     */
    @InjectMocks
    private SimpleMp4Downloader service;

    // Constants
    private static final String NOT_EXISTING_LINK = "http://www.random.resource/";

    @Test
    @Tag("basic")
    @DisplayName("Should load context")
    public void shouldLoadContext() {
        // Given
        // When
        // Then
        assertNotNull(service);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should download as resource")
    public void shouldDownloadAsResource() throws IOException {
        // Given
        URL url = resourceLoader.getResource("classpath:data/sample.mp4").getURL();
        // When
        Resource resource = service.downloadAsResource(url);
        // Then
        assertEquals(new UrlResource(url), resource);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should throw exception when resource does not exist")
    public void shouldThrowErrorIfResourceDoesNotExist() throws IOException {
        // Given
        URL url = new URL(NOT_EXISTING_LINK);
        // When
        FileDownloadException ex =
                assertThrows(FileDownloadException.class, () -> service.downloadAsResource(url));
        // Then
        assertEquals(String.format("Such file does not exist (%s)", url.toString()),
                ex.getMessage());
    }
}
