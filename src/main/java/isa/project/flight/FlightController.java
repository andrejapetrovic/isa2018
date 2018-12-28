package isa.project.flight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.project.airline.Airline;
import isa.project.airline.AirlineRepository;
import isa.project.airplane.AirplaneRepository;
import isa.project.destination.Destination;
import isa.project.destination.DestinationRepository;
import isa.project.flight.dto.FlightDto;
import isa.project.flight.dto.FlightFilterDto;
import isa.project.flight.dto.FlightReturnDto;
import isa.project.flight.dto.FlightSearchDto;
import isa.project.flight.fclass.FlightClassRepository;
import isa.project.flight.passenger.PassengerRepository;
import isa.project.segment.SegmentRepository;

@RestController
@RequestMapping(value="flight")
public class FlightController {

	@Autowired
	AirplaneRepository airplaneRepo;
	
	@Autowired 
	DestinationRepository destRepo;	
	
	@Autowired
	SegmentRepository segmentRepo;

	@Autowired
	AirlineRepository airlineRepo;
	
	@Autowired 
	FlightRepository flightRepo;
	
	@Autowired 
	FlightClassRepository fclassRepo;
	
	@Autowired
	PassengerRepository passengerRepo;
	
	@Autowired
	FlightService flightService;
	
	@RequestMapping(
			value = "{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Flight> getFlight(@PathVariable("id") Long id) {
		Flight flight = flightRepo.findOne(id);
		if(flight != null)
			return new ResponseEntity<Flight>(flight, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(
			value = "add",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Flight> add(@RequestBody FlightDto flightDto) throws Exception {
		Airline airline = airlineRepo.getOne(flightDto.getAirlineId());
		Flight flight = new Flight();
		flight.setDepartureDate(flightDto.getDepartDate());
		flight.setStopCount(flightDto.getStopCount());
		flight.setFrom(destRepo.findByAirportCode(flightDto.getFrom()));
		flight.setTo(destRepo.findByAirportCode(flightDto.getTo()));
		if(flightDto.getStopCount() > 0) 
			flight.setStops(destRepo.findAllByCodes(flightDto.getStopDestCodes()));
		flight.setAirplane(airplaneRepo.findByModelNumberAndModelName(flightDto.getAirplaneModelNumber(), flightDto.getAirplaneModelName()));
		flight.setAirline(airline);
		airline.getFlights().add(flight);
		return new ResponseEntity<Flight>(flightRepo.save(flight), HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value = "get-by-airline/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Flight>> getFlightByAirline(@PathVariable("id") Long id) {
		List<Flight> flights = flightRepo.findByAirlineId(id);
		return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "search",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FlightReturnDto> search(
			@Valid FlightFilterDto filterDto, @Valid FlightSearchDto searchDto
			) throws Exception {
		List<List<Flight>> foundFlights = flightService.searchAndFilter(searchDto, filterDto);
		if(foundFlights == null) {
			return new ResponseEntity<FlightReturnDto>(HttpStatus.NOT_FOUND);
		}
		List<Long> ids = new ArrayList<>(); 
		for (List<Flight> list : foundFlights) {
			ids.addAll(list.stream()
					.map(Flight::getId)
					.collect(Collectors.toList()));
		}
		List<Destination> stops = destRepo.findStops(ids);
		List<Airline> airlines = airlineRepo.findByFlights(ids);
		FlightReturnDto retVal = new FlightReturnDto();
		retVal.setAirlines(airlines);
		retVal.setFlights(foundFlights);
		retVal.setStops(stops);
		return new ResponseEntity<FlightReturnDto>(retVal, HttpStatus.FOUND);
	}
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
}
