package com.cache.manager.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUEvictionPolicy implements EvictionPolicy {
    private final Map<String,Long> accessTimeMap;
    private int capacity;
    public LRUEvictionPolicy(int capacity) {
        this.capacity =capacity;
        this.accessTimeMap = new LinkedHashMap<String, Long>(capacity,0.75f,true);
    }

    public void keyAccessed(String key) {
        this.accessTimeMap.put(key,System.currentTimeMillis());
    }

    public String evict() {
       String key= this.accessTimeMap.entrySet().iterator().next().getKey();
       accessTimeMap.remove(key);
       return key;
    }
}
