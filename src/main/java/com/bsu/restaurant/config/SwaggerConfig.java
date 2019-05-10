package com.bsu.restaurant.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration class to define swagger
 * 
 * @author bsu
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
                
                
    }
	
	private ApiInfo getApiInfo() {
	    return new ApiInfo(
	            "Spring Boot H2 Demo Rest API",
	            "Rest API for CRUD Operations",
	            "1.0",
	            "",
	            new Contact("B Shankar Upadhy","",""),
	            "",
	            "",
	            Collections.emptyList()
	    );

	}
	
}
