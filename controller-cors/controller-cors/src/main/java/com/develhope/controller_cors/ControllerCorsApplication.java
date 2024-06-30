package com.develhope.controller_cors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ControllerCorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControllerCorsApplication.class, args);
	}


}
