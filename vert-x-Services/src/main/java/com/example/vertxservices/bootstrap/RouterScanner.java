package com.example.vertxservices.bootstrap;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.example.vertxservices.annotation.VertXRouter;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Component
@Slf4j
public class RouterScanner implements BeanPostProcessor, BeanFactoryAware {
    BeanFactory beanFactory;

    // todo subClass
    // todo params
    @Override
    public Object postProcessBeforeInitialization(final Object bean, String beanName) throws BeansException {
        if (!beanFactory.containsBean("vertx") || !beanFactory.containsBean("router")) {
            log.error("can't find bean Vertx Bean,please check .....");
            return bean;
        }
        Class<?> beanClass = bean.getClass();

        String path = "";
        if (beanClass.isAnnotationPresent(VertXRouter.class)) {
            VertXRouter rootRouter = beanClass.getAnnotation(VertXRouter.class);
            path += rootRouter.value();
        }
        Method[] beanMethod = beanClass.getDeclaredMethods();
        for (Method method : beanMethod) {
            if (method.isAnnotationPresent(VertXRouter.class)) {
                VertXRouter methodRouter = method.getAnnotation(VertXRouter.class);
                Router router = beanFactory.getBean(Router.class);
                if (StringUtils.isNotEmpty(path) && !path.endsWith("/") && !methodRouter.value().startsWith("/")) {
                    path += "/";
                }
                path += methodRouter.value();
                router.route(path).handler(routingContext -> {
                    try {
                        if (method.getReturnType().equals(JsonObject.class)) {
                            routingContext.json(method.invoke(bean));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
