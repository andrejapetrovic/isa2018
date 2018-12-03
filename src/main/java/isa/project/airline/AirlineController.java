package isa.project.airline;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.project.destination.Destination;
import isa.project.destination.DestinationRepository;
import isa.project.flight.FlightRepository;
import isa.project.flight.pricelist.PriceListRepository;

@RestController
@RequestMapping(value="airline")
public class AirlineController {

	@Autowired
	AirlineRepository airlineRepo;
	
	@Autowired
	DestinationRepository destRepo;
	
	@Autowired
	FlightRepository flightRepo;
	
	@Autowired
	PriceListRepository pricelistRepo;
	
	@RequestMapping(
			value = "{name}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airline> get(@PathVariable("name") String name) {
		Airline airline = airlineRepo.findByNameContainingIgnoreCase(name);
		if(airline != null)
			return new ResponseEntity<Airline>(airline, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(
			value = "update/",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody AirlineDto airlineDto) throws Exception {
		Airline airline = airlineRepo.getOne(airlineDto.getId());
		airline.setName(airlineDto.getName());
		airline.setDescription(airlineDto.getDescription());
		airline.setAddress(airlineDto.getAddress());
		airlineRepo.save(airline);
		return new ResponseEntity(HttpStatus.OK);
	}
	
}
