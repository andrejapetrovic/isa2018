package isa.project.aircraft;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import isa.project.airline.Airline;
import isa.project.airline.AirlineRepository;

@RestController
@RequestMapping(value="airplane")
public class AircraftController {

	@Autowired
	AircraftRepository airplaneRepo;
	
	@Autowired
	AirlineRepository airlineRepo;
	
	@RequestMapping(
			value = "add",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Aircraft> add(@RequestBody AircraftDto airplaneDto) throws Exception {
		Aircraft plane = new Aircraft();
		plane.setModelName(airplaneDto.getModelName());
		plane.setModelNumber(airplaneDto.getModelNumber());
		Airline a = airlineRepo.getOne(airplaneDto.getOwnerId());
		a.getPlanes().add(plane);
		plane.setOwner(a);
		
		return new ResponseEntity<>(airplaneRepo.save(plane), HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value = "delete",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<?> delete(@RequestBody AircraftDto airplaneDto) throws Exception {
		Airline a = airlineRepo.getOne(airplaneDto.getOwnerId());
		Aircraft plane = airplaneRepo.findByModelNumberAndModelName(airplaneDto.getModelNumber(), airplaneDto.getModelName());
		a.getPlanes().remove(plane);
		airplaneRepo.delete(plane);
		airlineRepo.save(a);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "{modelName}/{modelNumber}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Aircraft> get(@PathVariable("modelName") String name, @PathVariable("modelNumber") int num) {
		Aircraft airplane = airplaneRepo.findByModelNumberAndModelName(num, name);
		if(airplane != null)
			return new ResponseEntity<Aircraft>(airplane, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(
			value = "all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Aircraft>> getAll() {
		List<Aircraft> airplanes = airplaneRepo.findAll();
		return new ResponseEntity<List<Aircraft>>(airplanes, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "owner/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Aircraft>> getByOwner(@PathVariable("id") Long id) {
		List<Aircraft> airplanes = airplaneRepo.findByOwnerId(id);
		return new ResponseEntity<List<Aircraft>>(airplanes, HttpStatus.OK);
	}
	
}
