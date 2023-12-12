package com.ism.controller;


import com.ism.model.Flight;
import com.ism.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    FlightService flightService;

    private static final String FLIGHT_NOT_EXIST = "There is no flight with this ID";
    private static final String FLIGHT_NUMBER_LENGTH_ERROR_MESSAGE = "Flight number must contain 4 digit number";
    private static final String FLIGHT_CODE_PATH_VARIABLE_PATTERN = "/{flightId}";

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        ArrayList<Flight> flights = (ArrayList<Flight>) flightService.findAllFlights();
        return ResponseEntity.ok().body(flights);
    }

    @GetMapping(FLIGHT_CODE_PATH_VARIABLE_PATTERN)
    public ResponseEntity<Object> getFlight(@PathVariable Long flightId) {
        Optional<Flight> flight = flightService.findFlightById(flightId);
        if(flight.isEmpty()){
            return ResponseEntity.badRequest().body(FLIGHT_NOT_EXIST);
        }
        return ResponseEntity.ok().body(flight.get());
    }

    @PostMapping
    public ResponseEntity<String> addFlight(@RequestBody Flight flight) {

        if(!flightService.CheckFNumberContainsFourDigitNumber(flight.getFlightNumber())){
            return ResponseEntity.badRequest().body(FLIGHT_NUMBER_LENGTH_ERROR_MESSAGE);
        }

        Flight addedFlight = flightService.addFlght(flight);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(FLIGHT_CODE_PATH_VARIABLE_PATTERN)
                    .buildAndExpand(addedFlight.getId()).toUri();
        return ResponseEntity.created(location).body(addedFlight.getFlightNumber());
    }

    @DeleteMapping(FLIGHT_CODE_PATH_VARIABLE_PATTERN)
    public ResponseEntity<String> deleteFlight(@PathVariable Long flightId) {
        Optional<Flight> flight = flightService.findFlightById(flightId);
        if (flight.isEmpty()) {
            return  ResponseEntity.badRequest().body(FLIGHT_NOT_EXIST);
        }
        // delete flight by id (void method)
        flightService.deleteFlightById(flightId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
