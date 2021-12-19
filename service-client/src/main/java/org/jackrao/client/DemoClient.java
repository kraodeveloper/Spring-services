package org.jackrao.client;

import com.alibaba.dubbo.config.annotation.Reference;
import org.jackrao.api.ApiServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class DemoClient {
    @Reference
    private ApiServices apiServices;
    @RequestMapping("/getCodeVersion")
    public String getCodeVersion(){
        return apiServices.getCodeVersion();
    }
}
