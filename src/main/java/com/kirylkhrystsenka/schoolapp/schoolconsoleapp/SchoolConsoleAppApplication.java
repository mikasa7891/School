package com.kirylkhrystsenka.schoolapp.schoolconsoleapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolConsoleAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolConsoleAppApplication.class, args);

		System.out.println("Привет, мир!");
	}

}
