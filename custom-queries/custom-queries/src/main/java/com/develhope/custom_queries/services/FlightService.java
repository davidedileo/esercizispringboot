package com.develhope.custom_queries.services;

import com.develhope.custom_queries.entities.Flight;
import com.develhope.custom_queries.entities.Status;
import com.develhope.custom_queries.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> provisioning(){
        List<Flight> flights = new ArrayList<>();
        for(int i = 0; i < 50; i++ ){
            Flight flight = new Flight();
            flight.setDescription(generateRandomString());
            flight.setFromAirport(generateRandomString());
            flight.setToAirport(generateRandomString());
            flight.setStatus(Status.ONTIME);
            flights.add(flight);
        }
        return flightRepository.saveAll(flights);
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
}
