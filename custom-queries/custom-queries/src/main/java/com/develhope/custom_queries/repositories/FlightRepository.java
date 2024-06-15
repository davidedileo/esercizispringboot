package com.develhope.custom_queries.repositories;

import com.develhope.custom_queries.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
