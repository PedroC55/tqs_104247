package com.ua.AirQuality.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Data {
    @JsonProperty("city_name") String city;
    @JsonProperty("data") Metrics[] metricsCollection;



    public Data(String city) {
        this.city = city;
    }
}