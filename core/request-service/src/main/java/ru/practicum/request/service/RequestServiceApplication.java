package ru.practicum.request.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class RequestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RequestServiceApplication.class, args);
    }

}
