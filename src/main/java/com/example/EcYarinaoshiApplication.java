package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EcYarinaoshiApplication extends SpringBootServletInitializer{ 

	public static void main(String[] args) throws Exception{ 
		SpringApplication.run(EcYarinaoshiApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) { 
		return application.sources(EcYarinaoshiApplication.class); 
		}
	

}
