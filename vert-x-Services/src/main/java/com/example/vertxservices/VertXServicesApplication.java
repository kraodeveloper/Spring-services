package com.example.vertxservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class VertXServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(VertXServicesApplication.class, args);

    }

}
