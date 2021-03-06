package org.jackrao.learning.cache.cacheManger;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfiguration {
    public static final long SYSTEM_DEFAULT_EXPIRES = 4 * 60 * 1000;

    /**
     * 用caffeine cache替换Spring内置的Guava cache，全局Cache Config，可以通过获取这个bean来调用get拿对应name的缓存
     */
    @Bean
    public CacheManager configCacheManager() {
        CaffeineCacheManager manager = new CaffeineCacheManager();
        manager.setCaffeine(Caffeine.newBuilder().expireAfterWrite(SYSTEM_DEFAULT_EXPIRES, TimeUnit.MILLISECONDS));
        return manager;
    }

    @Bean(name = "generalCache")
    public Cache getSettlementTTLCache() {
        return new CaffeineCache("settlement", Caffeine.newBuilder().expireAfterAccess(SYSTEM_DEFAULT_EXPIRES, TimeUnit.MILLISECONDS).build());
    }
}
