package com.karafra.bitchutedl.configuration.customizer;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.MimeMappings;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * Customizer for Tomcat servlet. This customizer had to be added because Spring did not want to
 * recognize application/woff2-font MIME type.
 * 
 * @author Karafra
 * @version 1.0
 * @since 1.0
 * @see <a href=
 *      "https://stackoverflow.com/questions/27617275/spring-mvc-boot-does-not-send-mime-type-for-certain-files-woff-etc">
 *      SO thread about this </a>
 */
@Component
public class ServletCustomizer
        implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
    /**
     * {@inheritDoc}
     */
    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
        mappings.add("woff", "application/x-font-woff");
        mappings.add("woff2", "application/font-woff2");
        factory.setMimeMappings(mappings);
    }
}
