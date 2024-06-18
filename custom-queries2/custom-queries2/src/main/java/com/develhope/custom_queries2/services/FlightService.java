package com.develhope.custom_queries2.services;

import com.develhope.custom_queries2.entities.Flight;
import com.develhope.custom_queries2.entities.Status;
import com.develhope.custom_queries2.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import static org.hibernate.annotations.UuidGenerator.Style.RANDOM;


@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> provisioning(int n){
        List<Flight> flights = new ArrayList<>();
        for(int i = 0; i < n; i++ ){
            Flight flight = new Flight();
            flight.setDescription(generateRandomString());
            flight.setFromAirport(generateRandomString());
            flight.setToAirport(generateRandomString());
            flight.setStatus(randomStatus());
            flights.add(flight);
        }
        return flightRepository.saveAll(flights);
    }

    public Page<Flight> getFlightPaged(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return flightRepository.getFlightPaged(pageable);
    }

    public List<Flight> getFlightByStatus(Status status) {
        return this.flightRepository.findByStatus(status);
    }

    public List<Flight> getFlightByTwoStatuses(Status status1, Status status2) {
        return this.flightRepository.findByStatusOrStatus(status1, status2);
    }

    public List<Flight> getAll(){
        return flightRepository.findAll();
    }

    //https://www.baeldung.com/java-random-string
    public String generateRandomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    //https://www.baeldung.com/java-enum-random-value
    private static final Random PRNG = new Random();

    public static Status randomStatus()  {
        Status[] statuses = Status.values();
        return statuses[PRNG.nextInt(statuses.length)];
    }


}
