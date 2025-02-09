package com.anemona.producer2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Producer2Application {

	public static void main(String[] args) {
		SpringApplication.run(Producer2Application.class, args);
	}

}
