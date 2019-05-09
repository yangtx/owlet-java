package com.xracoon.sys.owlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ImportResource("classpath:spring-web-servlet.xml")
public class OwletApplication {
	public static void main(String[] args) {
		SpringApplication.run(OwletApplication.class, args);
	}
}
