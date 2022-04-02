package com.example.vertxservices.bootstrap;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.impl.RouterImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class RouterInit implements SmartLifecycle, BeanFactoryAware {
    BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void start() {
        if (!beanFactory.containsBean("vertx") || !beanFactory.containsBean("router")) {
            log.error("can't find bean Vertx Bean,can't init Vertx Web");
            return;
        }
        Router router = beanFactory.getBean(Router.class);
        Vertx vertx = beanFactory.getBean(Vertx.class);
        vertx.createHttpServer().requestHandler(router).listen(8899).onSuccess(e -> log.info("success start vertx web services"));
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
