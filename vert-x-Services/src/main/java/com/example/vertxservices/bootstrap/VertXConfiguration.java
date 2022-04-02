package com.example.vertxservices.bootstrap;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.impl.RouterImpl;
import org.checkerframework.checker.units.qual.A;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.context.annotation.Configuration
public class VertXConfiguration {
    private Vertx vertx;

    @Bean("vertx")
    @RequestMapping
    public Vertx getVertx() {
        if(vertx!=null){
            return vertx;
        }
        vertx = Vertx.vertx();
        return vertx;
    }

    @Bean("router")
    public Router getRouter() {
        if(vertx == null){
            vertx = Vertx.vertx();
        }
        return Router.router(vertx);
    }
}
