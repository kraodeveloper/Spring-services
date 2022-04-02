package org.jackrao.learning.cache.cacheManger;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.function.Predicate;

@CacheConfig(cacheNames = "helperCache")
@Component
public class HelperCache<T> {
    @Resource
    CacheManager cacheManager;


    protected Predicate<T> predicate = t -> true;

    //手动通过cacheManager来操作缓存
    public void test() {
        Cache cache = cacheManager.getCache("helperCache");
        if (cache == null) {
            return;
        }
        cache.put("key", "value");
        cache.evict("key");
        cache.clear();
        cache.putIfAbsent("key", "value");
        //.....
    }
}
