package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring-config.xml")
public class SpringWebApplication {

    public static void main(String[] args) {
        
        SpringApplication.run(SpringWebApplication.class, args);
    }
}