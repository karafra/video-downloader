package com.karafra.bitchutedl.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.karafra.bitchutedl.exceptions.NotValidLinkException;
import com.karafra.bitchutedl.platforms.bitchute.BitchuteService;
import com.karafra.bitchutedl.platforms.bitchute.dtos.Download;
import com.karafra.bitchutedl.platforms.bitchute.dtos.DownloadPageProps;
import com.karafra.bitchutedl.platforms.bitchute.dtos.IndexPageForm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class WebControllerTests {

    @Mock
    private BitchuteService bitchuteService;

    /**
     * Tested component.
     */
    @InjectMocks
    private HomeController controller;

    // Constants
    private static final String MOCK_TEST_URL = "http://random.bictchute.video.com/";
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

    @BeforeEach
    private void setup() {
        MOCK_DOWNLOAD_PAGE_PROPS.setDownloads(new ArrayList<Download>());
        MOCK_DOWNLOAD_PAGE_PROPS.addDownload(MOCK_DOWNLOAD);
    }

    @Test
    @Tag("basic")
    @DisplayName("Controller context loads")
    public void ContextLoads() {
        // Given
        // When
        // Then
        assertNotNull(controller);
    }

    @Test
    @Tag("basic")
    @DisplayName("Should return index view")
    public void shouldDisplayIndexView() {
        // Given
        // When
        ModelAndView modelAndView = controller.index();
        // Then
        assertEquals("index.html", modelAndView.getViewName());
    }

    @Test
    @Tag("basic")
    @DisplayName("Should return download view")
    public void shouldDisplayDownloadPage() {
        // Given
        IndexPageForm indexPageForm = new IndexPageForm(MOCK_TEST_URL);
        Map<String, DownloadPageProps> model = new HashMap<>();
        model.put("props", MOCK_DOWNLOAD_PAGE_PROPS);
        when(bitchuteService.processVideo(MOCK_TEST_URL)).thenReturn(MOCK_DOWNLOAD_PAGE_PROPS);
        // When
        ModelAndView modelAndView = controller.download(indexPageForm);
        // Then
        verify(bitchuteService).processVideo(MOCK_TEST_URL);
        assertEquals("download-page.html", modelAndView.getViewName());
        assertEquals(model, modelAndView.getModel());
    }


    @Test
    @Tag("basic")
    @DisplayName("Should return download view")
    public void shouldRethrowErrorDownloadPage() {
        // Given
        IndexPageForm indexPageForm = new IndexPageForm(MOCK_TEST_URL);
        when(bitchuteService.processVideo(MOCK_TEST_URL))
                .thenThrow(new NotValidLinkException(MOCK_TEST_URL));
        // When
        NotValidLinkException ex =
                assertThrows(NotValidLinkException.class, () -> controller.download(indexPageForm));
        // Then
        verify(bitchuteService).processVideo(MOCK_TEST_URL);
        assertEquals(String.format("%s is not valid link", MOCK_TEST_URL), ex.getMessage());
    }

    @Test
    @Tag("basic")
    @DisplayName("When GET /download should redirect")
    public void shouldRedirectOnGetOnDownloadPage() throws IOException {
        // Given
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        // When
        controller.download(mockResponse);
        // Then
        verify(mockResponse).sendRedirect("/");
    }

    @Test
    @Tag("basic")
    @DisplayName("When GET /download should rethrow")
    public void shouldRethrowOnGetOnDownloadPage() throws IOException {
        // Given
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        doThrow(new IOException("Test exception")).when(mockResponse).sendRedirect("/");
        // When
        IOException ex = assertThrows(IOException.class, () -> controller.download(mockResponse));
        // Then
        verify(mockResponse).sendRedirect("/");
        assertEquals("Test exception", ex.getMessage());
    }
}
