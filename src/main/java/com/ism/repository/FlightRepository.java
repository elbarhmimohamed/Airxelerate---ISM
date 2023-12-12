package com.ism.repository;

import com.ism.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    // Custom query method for deleting a flight By ID
    void deleteFlightById(Long id);

}
