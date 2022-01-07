package com.karafra.bitchutedl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan(basePackages  = "com.karafra.bitchutedl" )
public class BitchuteDlApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitchuteDlApplication.class, args);
	}
}
