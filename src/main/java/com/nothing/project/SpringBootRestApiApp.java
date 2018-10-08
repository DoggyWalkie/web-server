package com.nothing.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.nothing.project"})
public class SpringBootRestApiApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestApiApp.class, args);
    }
}