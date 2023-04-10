package com.ua.AirQuality.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import com.ua.AirQuality.model.Data;

import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class WeatherBitRepository implements IAirQualityRepository {
    private static final Logger logger = Logger.getLogger(WeatherBitRepository.class.getName());
    private final String BASE_URL = "https://api.weatherbit.io/v2.0/";
    private final String API_KEY = "8b9b7bdc710042bd972b70ce1402e2e5";
    private final RestTemplate restTemplate = new RestTemplate();



    @Override
    public Data getByCity(String city) {
        ResponseEntity<String> response;
        try {
            response = restTemplate.getForEntity(BASE_URL
                    + "current/airquality?city=" + city + "&key=" + API_KEY, String.class);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Invalid API request");
            return null;
        }
        return processResponse(response);
    }

    private Data processResponse(ResponseEntity<String> response) {
        if (response.getBody() == null)
            return null;

        Data airData = null;
        var mapper = new ObjectMapper();

        try {
            airData = mapper.readValue(response.getBody(), Data.class);
        } catch (JsonProcessingException e) {
            logger.log(Level.WARNING, "Unexpected API response");
        }
        return airData;
    }
}