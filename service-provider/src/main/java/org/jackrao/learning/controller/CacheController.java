package org.jackrao.learning.controller;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CacheConfig(cacheNames = "ValidationTestController")
@RestController
public class CacheController {
    static Map<Integer, Integer> cacheMap = new HashMap<>();

    @Cacheable(key = "#s")
    @RequestMapping(value = "get/{s}", method = RequestMethod.GET)
    public Integer get(@PathVariable("s") int s) {
        return cacheMap.get(s);
    }

    @CacheEvict
    @RequestMapping(value = "put/{key}/{value}", method = RequestMethod.GET)
    public String put(@PathVariable Integer key, @PathVariable Integer value) {
        cacheMap.put(key, value);
        return "ok";
    }

    @RequestMapping(value = "set/{key}/{value}", method = RequestMethod.GET)
    public String set(@PathVariable Integer key, @PathVariable Integer value) {
        cacheMap.put(key, value);
        return "ok";
    }


}
