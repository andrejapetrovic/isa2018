package isa.project.flight.price;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.project.cabin.FlightClass;

public interface FlightPriceRepository extends JpaRepository<FlightPrice, Long>{

	FlightPrice findOneByFlightClassAndFlightId(FlightClass fClass, Long id);
	
}
