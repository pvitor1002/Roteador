package org.example.roteador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RoteadorApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RoteadorApplication.class);
        app.run(args);
    }
}
