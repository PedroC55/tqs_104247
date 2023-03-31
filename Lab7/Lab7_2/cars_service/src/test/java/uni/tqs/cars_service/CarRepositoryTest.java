package uni.tqs.cars_service;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;

import java.util.List;

import uni.tqs.cars_service.model.*;
import uni.tqs.cars_service.repository.*;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class CarRepositoryTest {

    // get a test-friendly Entity Manager
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository repository;

    @Test
    void whenFindCarByExistingId_thenReturnCar() {

        Car car = new Car("Peugeot", "Peugeot 208");
        car.setCarId(1L);
        entityManager.persistAndFlush(car);

        Car found = repository.findByCarId(car.getId());
        assertThat( found.getModel() ).isEqualTo(car.getModel());

    }

    @Test
    void whenInvalidId_thenReturnNull() {
        Car fromDb = repository.findByCarId(-99L);
        assertThat(fromDb).isNull();
    }

    @Test
    void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car car = new Car("Peugeot", "Peugeot 208");
        car.setCarId(1L);
        Car car2 = new Car("Renault", "Clio");
        car2.setCarId(2L);
        Car car3 = new Car("Ford", "Fiesta");
        car3.setCarId(3L);

        entityManager.persist(car);
        entityManager.persist(car2);
        entityManager.persist(car3);
        entityManager.flush();

        List<Car> allCars = repository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getMaker).containsOnly(car.getMaker(), car2.getMaker(), car3.getMaker());
    }
}