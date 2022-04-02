package com.example.vertxservices.annotation;

import io.vertx.core.http.HttpMethod;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface VertXRouter {
    @AliasFor("path")
    String value() default "/";

    int index() default 0;

    boolean nextRouter() default false;

    String httpMethod() default "GET";


}
