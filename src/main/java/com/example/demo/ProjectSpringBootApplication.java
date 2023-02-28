package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/*加上Scan才會去讀取以下的package*/
@ComponentScan({"com.example.demo","com.example.model","com.example.controller","com.example.service","com.example.repository"})
@EntityScan({"com.example.demo","com.example.model","com.example.controller","com.example.service","com.example.repository"})

public class ProjectSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectSpringBootApplication.class, args);
	}

}
