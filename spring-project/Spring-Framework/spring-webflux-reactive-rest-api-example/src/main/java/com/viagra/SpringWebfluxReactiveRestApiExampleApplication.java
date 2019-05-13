package com.viagra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;


@SpringBootApplication
@EnableReactiveMongoRepositories
public class SpringWebfluxReactiveRestApiExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxReactiveRestApiExampleApplication.class, args);
	}

}
