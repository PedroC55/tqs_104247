package com.ua.AirQuality.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.ua.AirQuality.cache.Cache;
import com.ua.AirQuality.model.Data;
import com.ua.AirQuality.service.AirQualityService;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AirQualityController.class)
public class AirQualityControllerTest {

    @Autowired
    MockMvc servlet;

    @MockBean
    AirQualityService service;

    @Test
    public void whenGetCache_thenReturnCache() throws Exception {
        when( service.getCache() ).thenReturn(new Cache(1));

        servlet.perform( MockMvcRequestBuilders.get("/api/cache")
                .contentType(MediaType.APPLICATION_JSON) )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("numRequests").value(0))
                .andExpect(jsonPath("numMisses").value(0))
                .andExpect(jsonPath("numHits").value(0));
    }



    @Test
    public void whenGetByValidCity_thenReturnValidAirData() throws Exception {
        when( service.getByCity( anyString() ) ).thenReturn( new Data("Aveiro") );

        servlet.perform( MockMvcRequestBuilders.get("/api/city/Aveiro")
                .contentType(MediaType.APPLICATION_JSON) )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("city_name").value("Aveiro"));
    }

    @Test
    public void whenGetByInvalidCity_thenReturnNotFound() throws Exception {
        when( service.getByCity( anyString() ) ).thenReturn( null );

        servlet.perform( MockMvcRequestBuilders.get("/api/city/Aveiro")
                .contentType(MediaType.APPLICATION_JSON) )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
