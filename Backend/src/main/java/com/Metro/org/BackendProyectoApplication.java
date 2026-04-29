package com.Metro.org;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean; // TE FALTABA ESTE
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // TE FALTABA ESTE
import org.springframework.security.crypto.password.PasswordEncoder; // TE FALTABA ESTE

@SpringBootApplication
public class BackendProyectoApplication implements CommandLineRunner { // AGREGADO EL IMPLEMENTS

    public static void main(String[] args) {
        SpringApplication.run(BackendProyectoApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Cargado exitosamente");
    }
}