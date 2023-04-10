package com.ua.AirQuality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ua.AirQuality.cache.Cache;
import com.ua.AirQuality.model.Data;
import com.ua.AirQuality.service.AirQualityService;


@RestController
@RequestMapping("/api/")

public class AirQualityController {

    @Autowired
    private AirQualityService service;

    @GetMapping("/cache")
    public ResponseEntity<Cache> getCache() {
        var request = service.getCache();
        if (request != null)
            return new ResponseEntity<>(request, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // @GetMapping("/geo")
    // public ResponseEntity<Data> getAirDataByCoords(@RequestParam(value = "lat") double lat,
    //                                                   @RequestParam(value = "lon") double lon) {
    //     Data request = service.getByCoords(lat, lon);
    //     if (request != null)
    //         return new ResponseEntity<>(request, HttpStatus.OK);
    //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }

    @GetMapping("/city/{city}")
    public ResponseEntity<Data> getAirDataByCity(@PathVariable String city) {
        Data request = service.getByCity(city);
        if (request != null)
            return new ResponseEntity<>(request, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}