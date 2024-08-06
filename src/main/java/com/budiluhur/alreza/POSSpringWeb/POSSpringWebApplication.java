package com.budiluhur.alreza.POSSpringWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class POSSpringWebApplication extends SpringBootServletInitializer{
	// public class DemoSpringWebApplication{

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(POSSpringWebApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(POSSpringWebApplication.class, args);
	}

}
