package com.Metro.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class BackendProyectoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BackendProyectoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Si sirve :D");
	}

}
