package com.cache.manager.model;

public interface EvictionPolicy {
    void keyAccessed(String key);
    String evict();
}
