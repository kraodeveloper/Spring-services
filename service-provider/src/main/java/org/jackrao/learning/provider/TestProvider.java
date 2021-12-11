package org.jackrao.learning.provider;

import com.alibaba.dubbo.config.annotation.Service;
import org.jackrao.api.ApiServices;
import reactor.core.publisher.Flux;
@Service
public class TestProvider implements ApiServices {
    @Override
    public String getCodeVersion() {
        return "hello,dubbo";
    }
}
