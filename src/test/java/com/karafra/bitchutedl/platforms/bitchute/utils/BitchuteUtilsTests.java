package com.karafra.bitchutedl.platforms.bitchute.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.karafra.bitchutedl.exceptions.NotValidLinkException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class BitchuteUtilsTests {

    private static final String VALID_LINK = "http://www.bitchute.com/video/randomId/";
    private static final String NOT_VALID_LINK = "http://www.portnhub.com/video/randomId/";
    private static final String BITCHUTE_ID = "bitchuteID";

    @Test
    @Tag("basic")
    @DisplayName("Should validate valid link")
    public void shouldValidateLink() {
        // Given
        // When
        boolean isValid = BitchuteUtils.isBitchuteLink(VALID_LINK);
        // Then
        assertTrue(isValid);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should convert id to Bitchute link")
    public void shouldConvertId() {
        // Given
        // When
        String bitchuteLink = BitchuteUtils.idToLink(BITCHUTE_ID);
        // Then
        assertEquals(String.format("https://www.bitchute.com/video/%s/", BITCHUTE_ID),
                bitchuteLink);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should not validate not valid link")
    public void shouldNotValidateLink() {
        // Given
        // When
        boolean isValid = BitchuteUtils.isBitchuteLink(NOT_VALID_LINK);
        // Then
        assertFalse(isValid);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should convert link to download link")
    public void shouldConvertToDownloadLink() {
        // Given
        // When
        String downloadLink = BitchuteUtils.generateDownloadLink(VALID_LINK);
        // Then
        assertEquals("http://localhost:8080/bitchute/randomId/download", downloadLink);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should not convert to download link if not valid link")
    public void shouldNotConvertToDownloadLink() {
        // Given
        // When
        NotValidLinkException ex = assertThrows(NotValidLinkException.class,
                () -> BitchuteUtils.generateDownloadLink(NOT_VALID_LINK));
        // Then
        assertEquals(String.format("%s is not valid link", NOT_VALID_LINK), ex.getMessage());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should convert link to view link")
    public void shouldConvertToViewLink() {
        // Given
        // When
        String downloadLink = BitchuteUtils.generateViewLink(VALID_LINK);
        // Then
        assertEquals("http://localhost:8080/bitchute/randomId/view", downloadLink);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should not convert to view link if not valid link")
    public void shouldNotConvertToViewLink() {
        // Given
        // When
        NotValidLinkException ex = assertThrows(NotValidLinkException.class,
                () -> BitchuteUtils.generateViewLink(NOT_VALID_LINK));
        // Then
        assertEquals(String.format("%s is not valid link", NOT_VALID_LINK), ex.getMessage());
    }
}
