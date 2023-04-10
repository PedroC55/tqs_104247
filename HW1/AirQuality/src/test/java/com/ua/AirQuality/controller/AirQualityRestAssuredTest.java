package com.ua.AirQuality.controller;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import com.ua.AirQuality.cache.Cache;
import com.ua.AirQuality.model.Data;

import com.ua.AirQuality.service.AirQualityService;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@WebMvcTest(AirQualityController.class)
public class AirQualityRestAssuredTest {

    @Autowired
    private MockMvc servlet;

    @MockBean
    private AirQualityService service;

    @BeforeEach
    public void associateAssured() {
        RestAssuredMockMvc.mockMvc(servlet);
    }

    @Test
    public void whenGetCache_thenReturnCache() {
        when( service.getCache() ).thenReturn(new Cache(1));

        RestAssuredMockMvc.given().when()
                .get("/api/cache")
                .then().assertThat().statusCode(200)
                .and().body("numRequests", equalTo(0))
                .and().body("numMisses", equalTo(0))
                .and().body("numHits", equalTo(0));

        verify(service, times(1)).getCache();
    }



    @Test
    public void whenGetByValidCity_thenReturnValidData() {
        when( service.getByCity( anyString() ) ).thenReturn( new Data("Aveiro") );

        RestAssuredMockMvc.given().when()
                .get("/api/city/Aveiro")
                .then().assertThat().statusCode(200)
                .and().body("city_name", equalTo("Aveiro"));

        verify(service, times(1)).getByCity(anyString());
    }

    @Test
    public void whenGetByInvalidCity_thenReturnNotFound() {
        when( service.getByCity( anyString() ) ).thenReturn( null );

        RestAssuredMockMvc.given().when()
                .get("/api/city/Aveiro")
                .then().assertThat().statusCode(404);

        verify(service, times(1)).getByCity(anyString());
    }
}