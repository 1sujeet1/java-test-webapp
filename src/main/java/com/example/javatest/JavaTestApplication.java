package com.example.javatest;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.javatest")
@EnableAsync
public class JavaTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaTestApplication.class, args);
	}
	 
	@Bean
	public ModelMapper getmodelMapper() {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
		
	}
}
