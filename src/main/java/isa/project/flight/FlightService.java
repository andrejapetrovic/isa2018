package isa.project.flight;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import isa.project.flight.dto.FlightFilterDto;
import isa.project.flight.dto.FlightSearchDto;
import isa.project.flight.dto.FlightType;
import isa.project.flight.dto.Sort;

public interface FlightService {

	public List<List<Flight>> searchAndFilter(FlightSearchDto searchDto, FlightFilterDto filterDto);
	
	//public List<Flight> filter(List<Long> ids, FlightFilterDto flightDto);
	
	public List<BigInteger> filter(
			List<BigInteger> ids, 
			FlightFilterDto filterDto
			);
	
	public List<Flight> searchOneWays(
			FlightSearchDto searchDto, FlightFilterDto filterDto 
			);
	
	List<List<Flight>> searchRoundTrips(FlightSearchDto searchDto, FlightFilterDto filterDto);

	List<Flight> sort(Sort sort, List<BigInteger> ids, Date takeoffTime, Date landingTime, FlightType type);
}
