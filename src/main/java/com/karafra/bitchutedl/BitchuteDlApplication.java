package com.karafra.bitchutedl;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import lombok.Generated;

/**
 * Spring boot initialization class
 * 
 * @author Karafra
 * 
 * @since 1.0
 * 
 * @version 1.0
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan(basePackages = "com.karafra.bitchutedl")
public class BitchuteDlApplication {

    /**
     * Method used for running this application from command line. This is the main method of whole
     * application.
     * 
     * @param args
     */
    @Generated
    public static void main(String[] args) {
        SpringApplication.run(BitchuteDlApplication.class, args);
    }
}
