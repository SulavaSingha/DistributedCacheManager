package com.cache.manager.model;

import com.cache.manager.Exception.KeyNotFoundException;
import com.sun.istack.internal.NotNull;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cache {
    Map<String,String> storage;
    private int capacity;
    private static Cache cache=null;
    private EvictionPolicy evictionPolicy;
    private Cache(){
        this.storage= new HashMap<String, String>();
    }

    public void setCapacity(@NotNull int capacity){
        this.capacity =capacity;
    }
    public void setEvictionPolicy(EvictionPolicy evictionPolicy){
        this.evictionPolicy = evictionPolicy;
    }
    public static Cache getInstance(){
        if(cache==null){
            return new Cache();
        }
        return cache;
    }
   public String get(String key) throws KeyNotFoundException {
        if(storage.containsKey(key)){
            updateCache(key);
            return storage.get(key);
        }
        else{
            throw new KeyNotFoundException("The Key is not present in cache");
        }
   }
   public void put(String key,String value){
       if(storage.size()==this.capacity){
           String evictionKey= evict(key);
           storage.remove(evictionKey);
       }
   }

    private String evict(String key) {
        return evictionPolicy.evict();
    }

    private void updateCache(String key) {
        evictionPolicy.keyAccessed(key);
    }
}
