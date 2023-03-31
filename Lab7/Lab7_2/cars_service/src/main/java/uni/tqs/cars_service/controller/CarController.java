package uni.tqs.cars_service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import uni.tqs.cars_service.exception.ResourceNotFoundException;
import uni.tqs.cars_service.model.Car;
import uni.tqs.cars_service.service.CarService;

@RestController
@RequestMapping("/api")
public class CarController {
    @Autowired
    private CarService service;

    @PostMapping("/createCar" )
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        HttpStatus status = HttpStatus.CREATED;
        Car saved = service.save(car);
        return new ResponseEntity<>(saved, status);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars(){
        return service.getAllCars();
    }

    @GetMapping("/carById/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) throws ResourceNotFoundException{
        HttpStatus status = HttpStatus.OK;
        Car finded = service.getCarDetails(id).orElseThrow(() -> new ResourceNotFoundException("CarId " + id + ", NOT FOUND."));
        return new ResponseEntity<>(finded, status);
    }
}