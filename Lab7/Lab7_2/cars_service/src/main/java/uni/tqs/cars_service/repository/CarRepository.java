package uni.tqs.cars_service.repository;

import java.util.List;

import uni.tqs.cars_service.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    public Car findByCarId(Long id);
    public List<Car> findAll();
}