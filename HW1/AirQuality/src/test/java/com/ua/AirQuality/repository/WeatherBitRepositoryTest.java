package com.ua.AirQuality.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import com.ua.AirQuality.model.Data;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class WeatherBitRepositoryTest {

    @InjectMocks
    private WeatherBitRepository repository;

    

    

    @Test
    public void whenGetByValidCity_thenReturnValidAirData() {
        String city = "Porto";
        Data airData = repository.getByCity(city);

        assertThat(airData).isInstanceOf(Data.class);
        assertThat(airData.getCity()).isEqualTo(city);
    }

    @Test
    public void whenGetByInvalidCity_thenReturnNull() {
        assertThat(repository.getByCity("?")).isNull();
    }
}
