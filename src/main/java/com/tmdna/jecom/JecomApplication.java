package com.tmdna.jecom;

import com.tmdna.jecom.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class JecomApplication {

	public static void main(String[] args) {
		SpringApplication.run(JecomApplication.class, args);
	}

}
