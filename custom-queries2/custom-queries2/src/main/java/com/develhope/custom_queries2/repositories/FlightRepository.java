package com.develhope.custom_queries2.repositories;

import com.develhope.custom_queries2.entities.Flight;
import com.develhope.custom_queries2.entities.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByStatus(Status status);

    List<Flight> findByStatusOrStatus(@Param("status1") Status status1, @Param("status2") Status status2);

    @Query(value = "SELECT * FROM flight ORDER BY from_airport", nativeQuery = true)
    Page<Flight> getFlightPaged(Pageable pageable);
}
