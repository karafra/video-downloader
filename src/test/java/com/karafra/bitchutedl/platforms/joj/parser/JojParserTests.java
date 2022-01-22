package com.karafra.bitchutedl.platforms.joj.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.karafra.bitchutedl.exceptions.NotValidLinkException;

import org.apache.tomcat.websocket.server.WsHandshakeRequest;
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
        when(mockJojParser.getLinkToEmbed(anyString())).thenCallRealMethod();
        when(mockJojParser.getLinkToEmbed()).thenReturn(expectedTarget);
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
        when(mockJojParser.getLinkToEmbed(anyString())).thenCallRealMethod();
        when(mockJojParser.getLinkToEmbed()).thenReturn(expectedTarget);
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

    @Test
    @Tag("basic")
    @DisplayName("Should get target by link")
    public void shouldGetTargetByLink() {
        // Given
        String target = "target";
        when(mockJojParser.getTarget()).thenReturn(target);
        when(mockJojParser.getTarget(anyString())).thenCallRealMethod();
        // When
        String targetResponse = mockJojParser.getTarget("link");
        // Then
        verify(mockJojParser).get(anyString());
        verify(mockJojParser).getTarget();
        assertEquals(target, targetResponse);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should get target by link")
    public void shouldNotGetTargetByLink() {
        // Given
        String target = "";
        when(mockJojParser.getTarget()).thenReturn(target);
        when(mockJojParser.getTarget(anyString())).thenCallRealMethod();
        // When
        String targetResponse = mockJojParser.getTarget("link");
        // Then
        verify(mockJojParser).get(anyString());
        verify(mockJojParser).getTarget();
        assertEquals(target, targetResponse);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should get target")
    public void shouldGetTarget() {
        // Given
        String expectedTarget = "target";
        when(mockJojParser.getTarget(anyString())).thenCallRealMethod();
        when(mockJojParser.getTarget()).thenReturn(expectedTarget);
        // When
        String target = mockJojParser.getTarget();
        // Then
        verify(mockJojParser, times(0)).getElementByXpath(anyString());
        assertTrue(target.equals(expectedTarget));
    }

    @Test
    @Tag("basic")
    @DisplayName("Should get target")
    public void shouldNotGetTarget() {
        // Given
        String expectedTarget = "";
        when(mockJojParser.getTarget(anyString())).thenCallRealMethod();
        when(mockJojParser.getTarget()).thenReturn(expectedTarget);
        // When
        String target = mockJojParser.getTarget();
        // Then
        verify(mockJojParser, times(0)).getElementByXpath(anyString());
        assertTrue(target.isBlank());
    }


    @Test
    @Tag("basic")
    @DisplayName("Should not extract if empty link")
    public void shouldNotExtractIfEmptyLink() throws IOException {
        // Given
        String emptyLink = "\t \n";
        when(mockJojParser.extract(anyString())).thenCallRealMethod();
        // When
        NotValidLinkException ex =
                assertThrows(NotValidLinkException.class, () -> mockJojParser.extract(emptyLink));
        // Then
        assertEquals(new NotValidLinkException(emptyLink).getMessage(), ex.getMessage());
    }

}
