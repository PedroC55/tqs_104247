package com.ua.AirQuality.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Metrics {
    @JsonProperty("aqi") private int aqi;
    @JsonProperty("ts") private long unixTime;
    @JsonProperty("co") private double co;
    @JsonProperty("no2") private double no2;
    @JsonProperty("o3") private double o3;
    @JsonProperty("so2") private double so2;
    @JsonProperty("pm10") private double pm10;
    @JsonProperty("pm25") @JsonAlias("pm2_5") private double pm25;
}