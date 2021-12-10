package com.moan.pet.health_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(scanBasePackages = {"com.moan.pet", "com.muad"})
@EnableAutoConfiguration
@Configuration
public class HealthService extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(HealthService.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HealthService.class);
	}
}
