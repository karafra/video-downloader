package com.karafra.bitchutedl.platforms.joj.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class JojUtilsTests {

    private static final String VALID_LINK =
            "https://videoportal.joj.sk/mafstory/epizoda/2080-vendeta/";

    private static final String NOT_VALID_LINK =
            "https://videoportal.joj.sk/random/pi/2080-vendeta/";

    @Test
    @Tag("basic")
    @DisplayName("Should validate valid tv joj link")
    public void shouldValidateJojLink() {
        // Given
        boolean result;
        // When
        result = JojUtils.isJojLink(VALID_LINK);
        // Then
        assertTrue(result);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should not validate valid not valid tv joj link")
    public void shouldNotValidateJojLink() {
        // Given
        boolean result;
        // When
        result = JojUtils.isJojLink(NOT_VALID_LINK);
        // Then
        assertFalse(result);
    }

}
