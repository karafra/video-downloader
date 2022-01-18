package com.karafra.bitchutedl.platforms.bitchute.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.karafra.bitchutedl.exceptions.NotValidLinkException;
import com.karafra.bitchutedl.platforms.bitchute.dtos.DownloadPageProps;
import com.karafra.bitchutedl.platforms.bitchute.utils.BitchuteUtils;

import org.jsoup.nodes.Element;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BitchuteParserTests {

    @Mock
    private BitchuteParser parser;

    @Test
    @Tag("basic")
    @DisplayName("Should get target")
    public void shouldGetTargetFromValidBitchuteVideo() {
        // Given
        String expectedTarget = "target";
        Element element = new Element("source");
        element.attr("src", expectedTarget);
        when(parser.getElementByXpath(anyString())).thenReturn(element);
        when(parser.getTarget()).thenCallRealMethod();
        // When
        String target = parser.getTarget();
        // Then
        verify(parser).getElementByXpath(anyString());
        assertEquals(expectedTarget, target);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should return empty string if target is not present")
    public void shouldNotGetTargetFromValidBitchuteVideo() {
        // Given
        Element element = new Element("source");
        when(parser.getElementByXpath(anyString())).thenReturn(element);
        when(parser.getTarget()).thenCallRealMethod();
        // When
        String target = parser.getTarget();
        // Then
        verify(parser).getElementByXpath(anyString());
        assertTrue(target.isEmpty());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should get target from link")
    public void shouldGetTargetFromLink() {
        // Given
        String expectedTarget = "target";
        when(parser.getTarget()).thenReturn(expectedTarget);
        when(parser.getTarget(anyString())).thenCallRealMethod();
        // When
        String target = parser.getTarget("url");
        // Then
        verify(parser).get(anyString());
        verify(parser).getTarget(anyString());
        assertEquals(expectedTarget, target);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should not get target from link if target not present")
    public void shouldNotGetTargetFromLink() {
        // Given
        when(parser.getTarget()).thenReturn(null);
        when(parser.getTarget(anyString())).thenCallRealMethod();
        // When
        String target = parser.getTarget("url");
        // Then
        verify(parser).get(anyString());
        verify(parser).getTarget(anyString());
        assertNull(target);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should get thumbnail link")
    public void shouldGetThumbnailLink() {
        // Given
        String expected = "poster";
        Element element = new Element("source");
        element.attr("poster", expected);
        when(parser.getElementByXpath(anyString())).thenReturn(element);
        when(parser.getThumbnailLink()).thenCallRealMethod();
        // When
        String thumbnailLink = parser.getThumbnailLink();
        // Then
        verify(parser).getElementByXpath(anyString());
        assertEquals(expected, thumbnailLink);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should not get thumbnail link")
    public void shouldNotGetThumbnailLink() {
        // Given
        Element element = new Element("source");
        when(parser.getElementByXpath(anyString())).thenReturn(element);
        when(parser.getThumbnailLink()).thenCallRealMethod();
        // When
        String thumbnailLink = parser.getThumbnailLink();
        // Then
        verify(parser).getElementByXpath(anyString());
        assertTrue(thumbnailLink.isEmpty());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should get thumbnail link by link")
    public void shouldGetThumbnailLinkByLink() {
        // Given
        String expected = "poster";
        when(parser.getThumbnailLink()).thenReturn(expected);
        when(parser.getThumbnailLink(anyString())).thenCallRealMethod();
        // When
        String thumbnailLink = parser.getThumbnailLink("anyString");
        // Then
        verify(parser).getThumbnailLink();
        assertEquals(expected, thumbnailLink);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should get not thumbnail link by link")
    public void shouldNotGetThumbnailLinkByLink() {
        // Given
        when(parser.getThumbnailLink()).thenReturn("");
        when(parser.getThumbnailLink(anyString())).thenCallRealMethod();
        // When
        String thumbnailLink = parser.getThumbnailLink("anyString");
        // Then
        verify(parser).getThumbnailLink();
        assertTrue(thumbnailLink.isEmpty());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should get thumbnail link")
    public void shouldGetPublisher() {
        // Given
        String expected = "publisher";
        Element element = new Element("a");
        element.text(expected);
        when(parser.getElementByXpath(anyString())).thenReturn(element);
        when(parser.getPublisher()).thenCallRealMethod();
        // When
        String publisher = parser.getPublisher();
        // Then
        verify(parser).getElementByXpath(anyString());
        assertEquals(expected, publisher);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should not get publisher")
    public void shouldNotGetPublisher() {
        // Given
        Element element = new Element("a");
        when(parser.getElementByXpath(anyString())).thenReturn(element);
        when(parser.getPublisher()).thenCallRealMethod();
        // When
        String publisher = parser.getPublisher();
        // Then
        verify(parser).getElementByXpath(anyString());
        assertTrue(publisher.isEmpty());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should get publisher by link")
    public void shouldGetPublisherByLink() {
        // Given
        String expected = "publisher";
        when(parser.getPublisher()).thenReturn(expected);
        when(parser.getPublisher(anyString())).thenCallRealMethod();
        // When
        String publisher = parser.getPublisher("anyString");
        // Then
        verify(parser).getPublisher();
        assertEquals(expected, publisher);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should not get publisher by link")
    public void shouldNotGetPublisherByLink() {
        // Given
        when(parser.getPublisher()).thenReturn("");
        when(parser.getPublisher(anyString())).thenCallRealMethod();
        // When
        String publisher = parser.getPublisher("anyString");
        // Then
        verify(parser).getPublisher();
        assertTrue(publisher.isEmpty());
    }


    @Test
    @Tag("basic")
    @DisplayName("Should get date")
    public void shouldGetDate() {
        // Given
        String expected = "date";
        Element element = new Element("div");
        element.text(expected);
        when(parser.getElementByXpath(anyString())).thenReturn(element);
        when(parser.getDate()).thenCallRealMethod();
        // When
        String publisher = parser.getDate();
        // Then
        verify(parser).getElementByXpath(anyString());
        assertEquals(expected, publisher);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should not get date")
    public void shouldNotGetDate() {
        // Given
        Element element = new Element("div");
        when(parser.getElementByXpath(anyString())).thenReturn(element);
        when(parser.getDate()).thenCallRealMethod();
        // When
        String publisher = parser.getDate();
        // Then
        verify(parser).getElementByXpath(anyString());
        assertTrue(publisher.isEmpty());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should get date by link")
    public void shouldGetDateByLink() {
        // Given
        String expected = "date";
        when(parser.getDate()).thenReturn(expected);
        when(parser.getDate(anyString())).thenCallRealMethod();
        // When
        String publisher = parser.getDate("anyString");
        // Then
        verify(parser).getDate();
        assertEquals(expected, publisher);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should not get date by link")
    public void shouldNotGetDateByLink() {
        // Given
        when(parser.getDate()).thenReturn("");
        when(parser.getDate(anyString())).thenCallRealMethod();
        // When
        String publisher = parser.getDate("anyString");
        // Then
        verify(parser).getDate();
        assertTrue(publisher.isEmpty());
    }


    @Test
    @Tag("basic")
    @DisplayName("Should get title")
    public void shouldGetTitle() {
        // Given
        String expected = "date";
        Element element = new Element("div");
        element.text(expected);
        when(parser.getElementByXpath(anyString())).thenReturn(element);
        when(parser.getTitle()).thenCallRealMethod();
        // When
        String title = parser.getTitle();
        // Then
        verify(parser).getElementByXpath(anyString());
        assertEquals(expected, title);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should not get title")
    public void shouldNotGetTitle() {
        // Given
        Element element = new Element("div");
        when(parser.getElementByXpath(anyString())).thenReturn(element);
        when(parser.getDate()).thenCallRealMethod();
        // When
        String title = parser.getDate();
        // Then
        verify(parser).getElementByXpath(anyString());
        assertTrue(title.isEmpty());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should not get title by link")
    public void shouldGetTitleByLink() {
        // Given
        String expected = "title";
        when(parser.getTitle()).thenReturn(expected);
        when(parser.getTitle(anyString())).thenCallRealMethod();
        // When
        String title = parser.getTitle("anyString");
        // Then
        verify(parser).getTitle();
        assertEquals(expected, title);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should not get title by link")
    public void shouldNotGetTitleByLink() {
        // Given
        when(parser.getTitle()).thenReturn("");
        when(parser.getTitle(anyString())).thenCallRealMethod();
        // When
        String publisher = parser.getTitle("anyString");
        // Then
        verify(parser).getTitle();
        assertTrue(publisher.isEmpty());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should get mp3 format of video")
    public void shouldGetMp3FormatOfVideo() {
        // Given
        String validBitchuteLink = "https://seed177.bitchute.com/IDo4as4O5ylC/5Tfn85JPO0Wz.mp4";
        when(parser.getFormat(anyString())).thenCallRealMethod();
        // When
        String format = parser.getFormat(validBitchuteLink);
        // Then
        assertEquals("mp4", format);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should get wav format of video")
    public void shouldGetWavFormatOfVideo() {
        // Given
        String validBitchuteLink = "https://seed177.bitchute.com/IDo4as4O5ylC/5Tfn85JPO0Wz.wav";
        when(parser.getFormat(anyString())).thenCallRealMethod();
        // When
        String format = parser.getFormat(validBitchuteLink);
        // Then
        assertEquals("wav", format);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should not extract if link is not from bitchute")
    public void shouldNoexpectedtExtractIfLinkIsNotFromBitchute() {
        // Given
        String badLink = "http://bad.link.com/video/randomidentifier";
        when(parser.extract(anyString())).thenCallRealMethod();
        // When
        NotValidLinkException ex =
                assertThrows(NotValidLinkException.class, () -> parser.extract(badLink));
        // Then
        assertEquals(String.format("%s is not valid link", badLink), ex.getMessage());
        verify(parser, times(0)).getFormat(anyString());
        verify(parser, times(0)).getPublisher();
        verify(parser, times(0)).getTitle();
        verify(parser, times(0)).getThumbnailLink();
    }

    @Test
    @Tag("basic")
    @DisplayName("Should extract if link is from bitchute")
    public void shouldExtract() {
        // Given
        String validLink = "http://www.bitchute.com/video/validId/";
        String validTarget = "target";
        String validFormat = "mp4";
        String validTitle = "title";
        String validPublisher = "publisher";
        String validThumbnailLink = "thumbnailLink";
        when(parser.getFormat(anyString())).thenReturn(validFormat);
        when(parser.getTarget()).thenReturn(validTarget);
        when(parser.getTitle()).thenReturn(validTitle);
        when(parser.getPublisher()).thenReturn(validPublisher);
        when(parser.getThumbnailLink()).thenReturn(validThumbnailLink);
        when(parser.extract(anyString())).thenCallRealMethod();
        // When
        DownloadPageProps props = parser.extract(validLink);
        // Then
        verify(parser).getFormat(anyString());
        verify(parser).getTarget();
        verify(parser).getTitle();
        verify(parser).getThumbnailLink();
        assertEquals(validPublisher, props.getAuthor());
        assertEquals(validFormat, props.getDownloads().get(0).getFormat());
        assertEquals(BitchuteUtils.generateDownloadLink(validLink),
                props.getDownloads().get(0).getLink());
        assertEquals(validTitle, props.getDescription());
        assertEquals(validThumbnailLink, props.getThumbnailLink());
        assertEquals(BitchuteUtils.generateViewLink(validLink), props.getPreviewLink());
        assertEquals(BitchuteUtils.generateDownloadLink(validLink), props.getQrDownLink());
    }
}
