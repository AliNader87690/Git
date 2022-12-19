package com.example.swtprojekt2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example")
public class Swtprojekt2Application {
	public static void main(String[] args) {
		SpringApplication.run(Swtprojekt2Application.class, args);
	}
}
