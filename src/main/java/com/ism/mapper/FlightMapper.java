package com.ism.mapper;

import com.ism.DTO.FlightDto;
import com.ism.model.Flight;
import org.apache.el.parser.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    @Autowired
    private ModelMapper modelMapper;

    public FlightDto convertToDto(Flight flight) {

        // FlightDto flightDto = modelMapper.map(flight, FlightDto.class); + add custom attr
        FlightDto flightDto = new FlightDto();

        flightDto.setId(flight.getId());
        flightDto.setFlightNumber(flight.getFlightNumber());
        flightDto.setCarrierCode(flight.getCarrierCode());
        flightDto.setFlightDate(flight.getFlightDate());
        flightDto.setOriginAirportCode(flight.getOriginAirportCode());
        flightDto.setDestinationAirportCode(flight.getDestinationAirportCode());

        return flightDto;
    }

    public Flight convertToEntity(FlightDto flightDto){

        // Flight flight = modelMapper.map(flightDto, Flight.class); + custom attr
        Flight flight = new Flight();

        flight.setId(flightDto.getId());
        flight.setFlightNumber(flightDto.getFlightNumber());
        flight.setCarrierCode(flightDto.getCarrierCode());
        flight.setFlightDate(flightDto.getFlightDate());
        flight.setOriginAirportCode(flightDto.getOriginAirportCode());
        flight.setDestinationAirportCode(flightDto.getDestinationAirportCode());

        return flight;
    }


}
