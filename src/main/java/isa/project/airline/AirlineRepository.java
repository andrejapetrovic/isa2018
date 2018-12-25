package isa.project.airline;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import isa.project.destination.Destination;

public interface AirlineRepository extends JpaRepository<Airline, Long>{

	Airline findByNameContainingIgnoreCase(String name);

	@Query(value = "SELECT * FROM AIRLINE WHERE airline_id IN" 
	+ "(SELECT airline_id FROM FLIGHT WHERE flight_id in :ids)"  
	,nativeQuery = true)
	List<Airline> findByFlights(@Param("ids") List<Long> flightIds);
}
