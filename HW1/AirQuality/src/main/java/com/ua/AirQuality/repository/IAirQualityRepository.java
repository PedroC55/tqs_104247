package com.ua.AirQuality.repository;

import com.ua.AirQuality.model.Data;

public interface IAirQualityRepository {
    Data getByCity(String city);
}