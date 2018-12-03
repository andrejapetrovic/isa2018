package isa.project.destination;

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

import isa.project.airline.Airline;
import isa.project.airline.AirlineRepository;

@RestController
@RequestMapping(value="dest")
public class DestinationController {

	@Autowired
	DestinationRepository destRepo;
	
	@Autowired
	AirlineRepository airlineRepo;
	
	@RequestMapping(
			value = "add-to-airline/",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Destination> addToAirline(@RequestBody DestinationDto destDto) throws Exception {
		Airline a = airlineRepo.getOne(destDto.getAirlineId());
		Destination dest  = destRepo.findByAirportCode(destDto.getAirportCode());
		if(dest == null) {
			dest = new Destination();
			dest.setAirportCode(destDto.getAirportCode());
			dest.setAirport(destDto.getAirport());
			dest.setCity(destDto.getCity());
			dest.setCountry(destDto.getCountry());
		}
		a.getDestinations().add(dest);
		airlineRepo.save(a);
		return new ResponseEntity<Destination>(dest, HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value = "delete-from-airline",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Destination> deleteFromAirline(@RequestBody DestinationDto destDto) throws Exception {
		Airline a = airlineRepo.getOne(destDto.getAirlineId());
		Destination dest  = destRepo.findByAirportCode(destDto.getAirportCode());
		a.getDestinations().remove(dest);
		airlineRepo.save(a);
		return new ResponseEntity<Destination>(dest, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "{code}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Destination> get(@PathVariable("code") String code) {
		Destination dest = destRepo.findByAirportCode(code);
		if(dest != null)
			return new ResponseEntity<Destination>(dest, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(
			value = "all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Destination>> getAll() {
		List<Destination> dests = destRepo.findAll();
		return new ResponseEntity<List<Destination>>(dests, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "airline/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Destination>> getByAirline(@PathVariable("id") Long id) {
		List<Destination> dests = destRepo.findByAirline(id);
		return new ResponseEntity<List<Destination>>(dests, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "airline/not-added/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Destination>> getNotAddedByAirline(@PathVariable("id") Long id) {
		List<Destination> dests = destRepo.findNonAddedByAirline(id);
		return new ResponseEntity<List<Destination>>(dests, HttpStatus.OK);
	}
}
