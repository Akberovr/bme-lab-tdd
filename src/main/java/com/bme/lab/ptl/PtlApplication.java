package com.bme.lab.ptl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class PtlApplication {
	public static void main(String[] args) {
		SpringApplication.run(PtlApplication.class, args);
	}
}
