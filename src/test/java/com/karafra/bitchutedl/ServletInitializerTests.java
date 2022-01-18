package com.karafra.bitchutedl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.builder.SpringApplicationBuilder;

@ExtendWith(MockitoExtension.class)
public class ServletInitializerTests {
    @Mock
    private SpringApplicationBuilder springApplicationBuilder;

    @Test
    @Tag("basic")
    @DisplayName("ServletInitializer test")
    public void testServletInitializer() {
        // Given
        ServletInitializer servletInitializer = new ServletInitializer();
        when(springApplicationBuilder.sources(BitchuteDlApplication.class))
                .thenReturn(springApplicationBuilder);
        // When
        SpringApplicationBuilder res = servletInitializer.configure(springApplicationBuilder);
        // Then
        verify(springApplicationBuilder).sources(BitchuteDlApplication.class);
        assertEquals(springApplicationBuilder, res);
    }
}
