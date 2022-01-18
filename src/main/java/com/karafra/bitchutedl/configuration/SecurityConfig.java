package com.karafra.bitchutedl.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration for web security.
 * 
 * @see <a href="https://spring.io/guides/gs/securing-web/">Documentation</a>
 * @version 1.0
 * @since 1.0
 * @category configuration
 * @author Karafra
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * {@inheritDoc}
     */
    @Override
    public void configure(HttpSecurity security) throws Exception {
        // Disables
        security.httpBasic().disable();
        security.formLogin().disable();
        security.headers().frameOptions().sameOrigin();
    }
}
