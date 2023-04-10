package com.ua.AirQuality.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AirQualityControllerIT {

    @Autowired
    private MockMvc servlet;

    @Test
    @Order(1)
    public void whenGetCacheBeforeRequests_thenReturnValidCache() throws Exception {
        servlet.perform( MockMvcRequestBuilders.get("/api/cache")
                .contentType(MediaType.APPLICATION_JSON) )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("numRequests").value(0))
                .andExpect(jsonPath("numMisses").value(0))
                .andExpect(jsonPath("numHits").value(0));
    }

    @Test
    @Order(2)
    public void whenGetCacheAfterSomeRequests_thenReturnValidCache() throws Exception {
        servlet.perform( MockMvcRequestBuilders.get("/api/city/Aveiro") );
        servlet.perform( MockMvcRequestBuilders.get("/api/city/Aveiro") );
        servlet.perform( MockMvcRequestBuilders.get("/api/city/Lisboa") );


        servlet.perform( MockMvcRequestBuilders.get("/api/v1/cache")
                .contentType(MediaType.APPLICATION_JSON) )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("numRequests").value(3))
                .andExpect(jsonPath("numMisses").value(2))
                .andExpect(jsonPath("numHits").value(2));
    }



    @Test
    @Order(3)
    public void whenGetByValidCity_thenReturnValidAirData() throws Exception {
        servlet.perform( MockMvcRequestBuilders.get("/api/city/Aveiro")
                .contentType(MediaType.APPLICATION_JSON) )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("city_name").value("Aveiro"));
    }

    @Test
    @Order(4)
    public void whenGetByInvalidCity_thenReturnNotFound() throws Exception {
        servlet.perform( MockMvcRequestBuilders.get("/api/city/c")
                .contentType(MediaType.APPLICATION_JSON) )
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}