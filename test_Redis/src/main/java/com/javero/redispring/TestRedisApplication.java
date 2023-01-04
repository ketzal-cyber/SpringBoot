package com.javero.redispring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TestRedisApplication {
	
	@Bean
	RestTemplate getRestTempalte() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(TestRedisApplication.class, args);
	}

}
