package com.karafra.bitchutedl.platforms.joj.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JojParserTests {

    @Mock
    private JojParser mockJojParser;

    @Test
    @Tag("basic")
    @DisplayName("Should get embed")
    public void shouldGetEmbed() {
        // Given
        String expectedTarget = "target";
        when(mockJojParser.getLinkToEmbed(anyString())).thenReturn(expectedTarget);
        when(mockJojParser.getLinkToEmbed()).thenCallRealMethod();
        // When
        String target = mockJojParser.getLinkToEmbed("");
        // Then
        verify(mockJojParser).getLinkToEmbed(anyString());
        assertTrue(target.equals(expectedTarget));
    }

    @Test
    @Tag("basic")
    @DisplayName("Should get embed")
    public void shouldNotGetEmbed() {
        // Given
        String expectedTarget = "";
        when(mockJojParser.getLinkToEmbed(anyString())).thenReturn(expectedTarget);
        when(mockJojParser.getLinkToEmbed()).thenCallRealMethod();
        // When
        String target = mockJojParser.getLinkToEmbed("");
        // Then
        verify(mockJojParser).getLinkToEmbed(anyString());
        assertTrue(target.isBlank());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should get embed by link")
    public void shouldGetEmbedWithoutLink() {
        // Given
        String expectedTarget = "target";
        Element element = new Element("iframe");
        element.attr("src", expectedTarget);
        when(mockJojParser.getElementByXpath(anyString())).thenReturn(element);
        when(mockJojParser.getLinkToEmbed()).thenCallRealMethod();
        // When
        String response = mockJojParser.getLinkToEmbed();
        // Then
        verify(mockJojParser, times(0)).getLinkToEmbed(anyString());
        verify(mockJojParser).getElementByXpath(anyString());
        assertEquals(expectedTarget, response);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should not get embed by link")
    public void shouldNotGetEmbedWithoutLink() {
        // Given
        String expectedTarget = "";
        Element element = new Element("iframe");
        when(mockJojParser.getElementByXpath(anyString())).thenReturn(element);
        when(mockJojParser.getLinkToEmbed()).thenCallRealMethod();
        // When
        String response = mockJojParser.getLinkToEmbed();
        // Then
        verify(mockJojParser, times(0)).getLinkToEmbed(anyString());
        verify(mockJojParser).getElementByXpath(anyString());
        assertEquals(expectedTarget, response);
    }
}
