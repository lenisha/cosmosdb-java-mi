package com.azure.cosmosdemo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.azure.spring.autoconfigure.cosmos.CosmosAutoConfiguration;

@SpringBootApplication (exclude=CosmosAutoConfiguration.class)

public class DemoApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
