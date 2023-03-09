package uni.tqs.cars_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import uni.tqs.cars_service.model.*;
import uni.tqs.cars_service.repository.*;
import uni.tqs.cars_service.service.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock( lenient = true)
    private CarRepository repository;

    @InjectMocks
    private CarService service;

    @BeforeEach
    public void setUp() {

        Car car = new Car("Peugeot", "Peugeot 208");
        car.setCarId(1L);

        Car car2 = new Car("Renault", "Clio");
        Car car3 = new Car("Ford", "Fiesta");

        List<Car> allCars = Arrays.asList(car, car2, car3);

        Mockito.when(repository.findByCarId(car.getId())).thenReturn(car);
        Mockito.when(repository.findAll()).thenReturn(allCars);
        Mockito.when(repository.findByCarId(-99L)).thenReturn(null);

    }

    @Test
     void whenValidId_thenCarShouldBeFound() {

        Car fromDb = service.getCarDetails(1L).get();
        assertThat(fromDb.getMaker()).isEqualTo("Peugeot");
        
        verifyFindByIdIsCalledOnce();

    }

    @Test
     void whenInValidId_thenCarShouldNotBeFound() throws NullPointerException {

        assertThrows(NullPointerException.class, () -> {
            Optional<Car> fromDb = service.getCarDetails(99L);
            assertThat(fromDb).isNull();

        });

        verifyFindByIdIsCalledOnce();

    }

    @Test
     void given3Cars_whengetAll_thenReturn3Records() {

        Car car = new Car("Peugeot", "Peugeot 208");
        Car car2 = new Car("Renault", "Clio");
        Car car3 = new Car("Ford", "Fiesta");

        List<Car> allCars = service.getAllCars();
        verifyFindAllCarsIsCalledOnce();
        assertThat(allCars).hasSize(3).extracting(Car::getMaker).contains(car.getMaker(), car2.getMaker(), car3.getMaker());

    }

    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(repository, VerificationModeFactory.times(1)).findByCarId(Mockito.anyLong());
    }

    private void verifyFindAllCarsIsCalledOnce() {
        Mockito.verify(repository, VerificationModeFactory.times(1)).findAll();
    }


}