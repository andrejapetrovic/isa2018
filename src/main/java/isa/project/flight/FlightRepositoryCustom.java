package isa.project.flight;

import java.util.List;

import isa.project.flight.dto.FlightFilterDto;

public interface FlightRepositoryCustom {

	public List<Flight> filterQuery(FlightFilterDto flightSearchDto);
	
}
