package com.bsu.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.bsu.restaurant")
public class SpringBootH2DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootH2DemoApplication.class, args);
	}

}
