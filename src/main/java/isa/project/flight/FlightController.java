package isa.project.flight;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.project.airplane.AirplaneRepository;
import isa.project.destination.Destination;
import isa.project.destination.DestinationRepository;
import isa.project.segment.Segment;
import isa.project.segment.SegmentRepository;

@RestController
@RequestMapping(value="flight")
public class FlightController {

	@Autowired
	FlightService flightService;
	
	@Autowired
	AirplaneRepository airplaneRepo;
	
	@Autowired 
	DestinationRepository destRepo;	
	
	@Autowired
	SegmentRepository segmentRepo;
	
	@RequestMapping(
			value = "{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Flight> getFlight(@PathVariable("id") Long id) {
		Flight flight = flightService.findById(id);
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
		Flight flight = new Flight();
		flight.setAirplane(airplaneRepo.getOne(flightDto.getAirplaneId()));
		flight.setDepartDate(flightDto.getDepartDate());
		flight.setReturnDate(flightDto.getReturnDate());
		flight.setStopCount(flightDto.getStopCount());
		flight.setFrom(destRepo.findOne(flightDto.getStartingDestId()));
		flight.setTo(destRepo.findOne(flightDto.getEndingDestId()));
		flight.setStops(destRepo.findAll(flightDto.getStopIds()));
		flight.setSegments(segmentRepo.findAll(flightDto.getSegmentsId()));
		flightService.save(flight);
		return new ResponseEntity<Flight>(flight, HttpStatus.CREATED);
	}
	
}
