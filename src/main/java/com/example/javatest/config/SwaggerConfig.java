package com.example.javatest.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket apiDocV1() {
		Set<String> version = new HashSet<>();
		version.add(MediaType.APPLICATION_JSON_VALUE);
		return new Docket(DocumentationType.SWAGGER_2).produces(version).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.javatest.controller")).build();
	}
	

}
