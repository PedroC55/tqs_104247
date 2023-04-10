package com.ua.AirQuality.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ua.AirQuality.cache.Cache;
import com.ua.AirQuality.model.Data;
import com.ua.AirQuality.repository.WeatherBitRepository;


@Service
public class AirQualityService {
    private final Cache cache = new Cache(60);

    @Autowired
    private WeatherBitRepository repository1;

    

    public Cache getCache() {
        return cache;
    }



    public Data getByCity(String city) {
        String key = "getByCity(" + city + ")";
        Data request = cache.getCachedRequest(key);

        if (request != null)
            return request;

        request = repository1.getByCity(city);
        

        cache.cacheRequest(key, request);
        return request;
    }
}