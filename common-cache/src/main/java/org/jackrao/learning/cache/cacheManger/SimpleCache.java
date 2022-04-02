package org.jackrao.learning.cache.cacheManger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@CacheConfig(cacheNames = "simpleCache")
@Component
public class SimpleCache {


    static Map<Integer, Integer> cacheMap = new HashMap<>();

    @Cacheable(key = "#s")
    public Integer get(int s) {
        return cacheMap.get(s);
    }

    /**
     * 同步set
     */
    @CacheEvict
    @Cacheable(sync = true)
    public String syncSet(Integer key, Integer value) {
        cacheMap.put(key, value);
        return "ok";
    }

    /**
     * 条件 set
     */
    @Cacheable(condition = "key == 1")
    public String conditionSet(Integer key, Integer value) {
        cacheMap.put(key, value);
        return "ok";
    }

    /**
     * put另外的cache
     */
    @CachePut(cacheNames = "helperCache", key = "#key")
    public String helperCacheSet(Integer key, Integer value) {
        cacheMap.put(key, value);
        return "ok";
    }

    @CacheEvict
    public String put(Integer key, Integer value) {
        cacheMap.put(key, value);
        return "ok";
    }
    @CacheEvict(allEntries = true)
    public String clear(Integer key, Integer value) {
        cacheMap.put(key, value);
        return "ok";
    }

    @CacheEvict
    public String set(Integer key, Integer value) {
        cacheMap.put(key, value);
        return "ok";
    }
}
