package com.example.services;

import org.reactivestreams.Subscription;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@SpringBootApplication
public class ServicesApplication {

    public static void main(String[] args) throws InterruptedException {
    SimpleSubscriber subscriber = new SimpleSubscriber();
    Flux<String> flux = Flux.just("Hello", "World");
    flux.subscribe(subscriber);

    }
}
