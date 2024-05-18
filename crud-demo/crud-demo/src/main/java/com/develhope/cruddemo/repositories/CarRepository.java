package com.develhope.cruddemo.repositories;

import com.develhope.cruddemo.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
