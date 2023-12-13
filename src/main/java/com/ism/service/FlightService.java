package com.ism.service;

import com.ism.dto.FlightDto;
import com.ism.mapper.FlightMapper;
import com.ism.model.Flight;
import com.ism.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    FlightMapper flightMapper;


    public List<FlightDto> findAllFlights(){

        List<Flight> flights = flightRepository.findAll();
        List<FlightDto> flightDtos = flights.stream().map(f ->flightMapper.convertToDto(f))
                .collect(Collectors.toList());

        return flightDtos;
    }

    public FlightDto findFlightById(Long id){

        FlightDto flightDto = null;
        Optional<Flight> flight = flightRepository.findById(id);

        if(flight.isPresent()){
            flightDto = flightMapper.convertToDto(flight.get());
        }
        return flightDto;
    }

    public FlightDto addFlght(FlightDto flightDto) {

        Flight flight = flightMapper.convertToEntity(flightDto);
        Flight addFlight = flightRepository.save(flight);

        return flightMapper.convertToDto(addFlight);
    }
    @Transactional
    public void deleteFlightById(Long id){
         flightRepository.deleteById(id);
    }

    public boolean CheckFNumberContainsFourDigitNumber(String number) {

        String regex = "\\b\\d{4}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        return matcher.find();
    }

}
