package com.example.sarmad_back_end_task;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@EnableRabbit
public class SarmadBackEndTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(SarmadBackEndTaskApplication.class, args);
	}

}
