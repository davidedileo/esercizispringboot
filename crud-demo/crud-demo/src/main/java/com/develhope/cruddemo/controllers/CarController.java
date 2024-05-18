package com.develhope.cruddemo.controllers;

import com.develhope.cruddemo.entities.Car;
import com.develhope.cruddemo.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @PostMapping
    public Car create(@RequestBody Car car){
        Car carCreated = carRepository.saveAndFlush(car);
        return carCreated;
    }

    @GetMapping
    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getSingleCar(@PathVariable Long id){
        if (carRepository.existsById(id)) {
            Car car = carRepository.findById(id).orElse(null);
            return ResponseEntity.ok(car);
        } else {
            Car emptyCar = new Car();
            return ResponseEntity.ok(emptyCar);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCarType(@PathVariable Long id, @RequestParam String type){
        if (carRepository.existsById(id)) {
            Car car = carRepository.findById(id).get();
            car.setType(type);
            return ResponseEntity.ok(carRepository.save(car));
        } else {
            return ResponseEntity.ok(new Car());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping
    public void deleteAllCars() {
        carRepository.deleteAll();
    }


}
