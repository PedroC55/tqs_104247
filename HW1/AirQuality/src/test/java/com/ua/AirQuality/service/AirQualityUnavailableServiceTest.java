package com.ua.AirQuality.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ua.AirQuality.model.Data;
import com.ua.AirQuality.repository.WeatherBitRepository;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AirQualityUnavailableServiceTest {

    @Mock(lenient = true)
    private WeatherBitRepository repository1;



    @InjectMocks
    private AirQualityService service;

    @BeforeEach
    public void setUp() {
        

        
        when(repository1.getByCity("Aveiro")).thenReturn(null);
        
        when(repository1.getByCity("?")).thenReturn(null);

        
    }

   
    @Test
    public void whenGetByAnyCity_thenReturnNull() {
        Data cityRequest1 = service.getByCity("Aveiro");
        Data cityRequest2 = service.getByCity("?");

        assertThat(cityRequest1).isNull();
        assertThat(cityRequest2).isNull();
    }
}