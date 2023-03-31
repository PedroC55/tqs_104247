package uni.tqs.cars_service.service;


import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import uni.tqs.cars_service.model.Car;
import uni.tqs.cars_service.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CarService {
    @Autowired
    private CarRepository repository;

    public Car save(Car car){
        return repository.save(car);
    }

    public List<Car> getAllCars(){
        return repository.findAll();
    }

    public Optional<Car> getCarDetails(Long id){
        return Optional.of(repository.findByCarId(id));
    }
}