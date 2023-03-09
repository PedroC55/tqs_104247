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
        entityManager.persistAndFlush(car);

        Car fromDb = null;
        fromDb = repository.findByCarId(car.getId());
        assertThat(fromDb).isNotNull();
        assertThat(fromDb.getMaker()).isEqualTo( car.getMaker());

    }

    @Test
    void whenInvalidId_thenReturnNull() {
        Car fromDb = repository.findById(-111L).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car car = new Car("Peugeot", "Peugeot 208");
        Car car2 = new Car("Renault", "Clio");
        Car car3 = new Car("Ford", "Fiesta");

        entityManager.persist(car);
        entityManager.persist(car2);
        entityManager.persist(car3);
        entityManager.flush();

        List<Car> allCars = repository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getMaker).containsOnly(car.getMaker(), car2.getMaker(), car3.getMaker());
    }
}