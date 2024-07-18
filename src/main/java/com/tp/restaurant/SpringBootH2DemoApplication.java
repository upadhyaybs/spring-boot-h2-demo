package com.tp.restaurant;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Restaurant API", version = "1.0", description = "Restaurant API Documentation"))
public class SpringBootH2DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootH2DemoApplication.class, args);
	}

}
