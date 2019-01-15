package isa.project.airline;

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

import isa.project.aircraft.Aircraft;
import isa.project.aircraft.AircraftRepository;
import isa.project.destination.Destination;
import isa.project.destination.DestinationRepository;
import isa.project.flight.Flight;
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
	
	@Autowired
	AircraftRepository aircraftRepo;
	
	@RequestMapping(
			value = "{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airline> get(@PathVariable("id") Long id) {
		Airline airline = airlineRepo.findOne(id);
		if(airline != null)
			return new ResponseEntity<Airline>(airline, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(
			value = "profile/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AirlineProfileDto> getProfileInfo(@PathVariable("id") Long id) {
		Airline airline = airlineRepo.findOne(id);
		if(airline == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		List<Flight> flights = flightRepo.findByAirlineId(airline.getId());
		List<Destination> destinations = destRepo.findByAirline(airline.getId());
		List<Aircraft> aircrafts = aircraftRepo.findByOwnerId(airline.getId());
		AirlineProfileDto airlineProfile = new AirlineProfileDto();
		airlineProfile.setAircrafts(aircrafts);
		airlineProfile.setAirline(airline);
		airlineProfile.setDestinations(destinations);
		airlineProfile.setFlights(flights);
		return new ResponseEntity<AirlineProfileDto>(airlineProfile, HttpStatus.OK);
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
		return new ResponseEntity<>(airlineRepo.save(airline), HttpStatus.OK);
	}

	@RequestMapping(
			value = "get-all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Airline>> getAll() {
		List<Airline> airlines = airlineRepo.findAll();
		if(airlines != null)
			return new ResponseEntity<List<Airline>>(airlines, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
