package com.example.vertxservices.test;

import com.example.vertxservices.annotation.VertXRouter;
import io.vertx.core.json.JsonObject;
import org.springframework.stereotype.Component;

@Component
@VertXRouter("/test")
public class VertXRouterTest {
    @VertXRouter("/test")
    public JsonObject test() {
        return new JsonObject()
                .put("name", 1)
                .put("address", 1)
                .put("message", "Hello " + 1 + " connected from " + 1);
    }
}
