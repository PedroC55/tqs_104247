package com.ua.AirQuality.cache;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ua.AirQuality.model.Data;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    @JsonIgnore
    private final int expDuration;

    private int numRequests = 0;
    private int numHits = 0;
    private int numMisses = 0;

    @JsonIgnore
    private final Map<String, Data> requests = new HashMap<>();
    @JsonIgnore
    private final Map<String, Long> expiration = new HashMap<>();

    public Cache(int expDuration) {
        this.expDuration = expDuration;
    }

    public int getNumRequests() {
        return numRequests;
    }

    public int getNumHits() {
        return numHits;
    }

    public int getNumMisses() {
        return numMisses;
    }

    public Data getCachedRequest(String key) {
        numRequests++;
        if (requests.containsKey(key)) {
            if (expired(key)) {
                remCachedRequest(key);
                numMisses++;
                return null;
            }
            numHits++;
            return requests.get(key);
        }
        numMisses++;
        return null;
    }

    public void cacheRequest(String key, Data request) {
        requests.put(key, request);
        expiration.put(key, getCurTime() + expDuration * 1000L);
    }

    private void remCachedRequest(String key) {
        requests.remove(key);
        expiration.remove(key);
    }

    private boolean expired(String key) {
        Long expTime = expiration.get(key);
        return getCurTime() > expTime;
    }

    private long getCurTime() {
        return System.currentTimeMillis();
    }
}