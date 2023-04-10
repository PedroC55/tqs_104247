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
public class AirQualityServiceTest {

    @Mock(lenient=true)
    private WeatherBitRepository repository1;



    @InjectMocks
    private AirQualityService service;

    @BeforeEach
    public void setUp() {
        Data cityRequest = new Data("Aveiro");
        

        
        when( repository1.getByCity("Aveiro") ).thenReturn( cityRequest );
        when( repository1.getByCity("?") ).thenReturn( null );

        
    }

    


    @Test
    public void whenGetByValidCity_thenReturnRequest() {
        Data validCityRequest = service.getByCity("Aveiro");
        assertThat(validCityRequest).isNotNull();
        assertThat(validCityRequest.getCity()).isEqualTo("Aveiro");
    }

    @Test
    public void whenGetByInvalidCity_thenReturnNull() {
        Data invalidCityRequest = service.getByCity("?");
        assertThat(invalidCityRequest).isNull();
    }
}