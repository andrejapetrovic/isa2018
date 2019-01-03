package isa.project.aircraft;

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

import isa.project.airline.Airline;
import isa.project.airline.AirlineRepository;
import isa.project.cabin.Cabin;
import isa.project.cabin.CabinRepository;
import isa.project.seat.Seat;
import isa.project.seat.SeatRepository;

@RestController
@RequestMapping(value="airplane")
public class AircraftController {

	@Autowired
	AircraftRepository airplaneRepo;
	
	@Autowired
	AirlineRepository airlineRepo;
	
	@Autowired
	CabinRepository cabinRepo;
	
	@Autowired
	SeatRepository seatRepo;
	
	@RequestMapping(
			value = "add",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Aircraft> add(@RequestBody AircraftDto airplaneDto) throws Exception {
		Aircraft plane = new Aircraft();
		plane.setModelName(airplaneDto.getModelName());
		plane.setModelNumber(airplaneDto.getModelNumber());
		plane.setType(AircraftType.valueOf(airplaneDto.getType()));
		Airline a = airlineRepo.getOne(airplaneDto.getOwnerId());
		a.getPlanes().add(plane);
		plane.setOwner(a);
		
		return new ResponseEntity<>(airplaneRepo.save(plane), HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value = "delete/{aircraftId}/{airlineId}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Aircraft> delete(@PathVariable("aircraftId") Long aircraftId,
			@PathVariable("airlineId") Long airlineId) throws Exception {
		Airline a = airlineRepo.findOne(airlineId);
		Aircraft plane = airplaneRepo.findOne(aircraftId);
		a.getPlanes().remove(plane);
		airlineRepo.save(a);
		airplaneRepo.delete(plane.getId());
		return new ResponseEntity<Aircraft>(plane, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AircraftReturnDto> get(@PathVariable("id") Long id) {
		AircraftReturnDto ret = new AircraftReturnDto();
		Aircraft airplane = airplaneRepo.findOne(id);
		if(airplane == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		ret.setAircraft(airplane);
		List<Cabin> cabins = cabinRepo.findByAircraftId(airplane.getId());
		if(cabins != null) {
			ret.setCabins(cabins);
			List<Seat> seats = new ArrayList<>();
			cabins.forEach(cabin -> seats.addAll(seatRepo.findByCabinId(cabin.getId())));
			ret.setSeats(seats);
		}
		return new ResponseEntity<AircraftReturnDto>(ret, HttpStatus.OK);
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
