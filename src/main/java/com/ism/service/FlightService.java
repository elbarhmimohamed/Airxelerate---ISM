package com.ism.service;

import com.ism.model.Flight;
import com.ism.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;

    public List<Flight> findAllFlights(){
        return flightRepository.findAll();
    }

    public Optional<Flight> findFlightById(Long id){
        return flightRepository.findById(id);
    }

    public Flight addFlght(Flight flight){
        return flightRepository.save(flight);
    }
    @Transactional
    public void deleteFlightById(Long id){
         flightRepository.deleteFlightById(id);
    }

    public boolean CheckFNumberContainsFourDigitNumber(String number) {

        String regex = "\\b\\d{4}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        return matcher.find();
    }

}
