package isa.project.flight.seat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import isa.project.user.Message;

@RestController
@RequestMapping(value = "flight-seat")
public class FlightSeatController {

	@Autowired
	FlightSeatRepository flightSeatRepo;
	
	@RequestMapping(
			value = "/get",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<List<FlightSeat>>> get(
			@RequestParam("fl") Long flightId,
			@RequestParam(value="ret", required=false) Long retFlightId
			) {
		
		List<FlightSeat> flSeats = flightSeatRepo.findByFlightId(flightId);
		if(flSeats == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		List<List<FlightSeat>> ret = new ArrayList<>();
		ret.add(flSeats);
		
		List<FlightSeat> flRetSeats = flightSeatRepo.findByFlightId(retFlightId);
		if(flRetSeats == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		ret.add(flRetSeats);
		
		return new ResponseEntity<List<List<FlightSeat>>>(ret, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_AirlineAdmin')")
	@RequestMapping(
			value = "/ids",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<List<FlightSeat>>> getByIds(
			@RequestParam("seats") String seatsStr,
			@RequestParam(value="retSeats", required=false) String retSeatsStr) {
		
		List<Long> seatIds = Arrays.stream(seatsStr.split("-"))
			.map(Long::parseLong)
			.collect(Collectors.toList());
		
		List<FlightSeat> flSeats = flightSeatRepo.findAll(seatIds);
		if(flSeats == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		List<List<FlightSeat>> ret = new ArrayList<>();
		ret.add(flSeats);
		
		if(retSeatsStr != null) {
			List<Long> retSeatIds = Arrays.stream(retSeatsStr.split("-"))
					.map(Long::parseLong)
					.collect(Collectors.toList());
			List<FlightSeat> flRetSeats = flightSeatRepo.findAll(retSeatIds);
			if(flRetSeats == null)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			ret.add(flRetSeats);
			
		}
		
		return new ResponseEntity<List<List<FlightSeat>>>(ret, HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_AirlineAdmin')")
	@RequestMapping(
			value = "/get/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> get(
			@PathVariable("id") Long id
			) {
		
		List<FlightSeat> flSeats = flightSeatRepo.findByFlightId(id);
		if(flSeats == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(flSeats, HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_AirlineAdmin')")
	@RequestMapping(
			value = "/delete",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(
			@RequestBody FlightSeat fs
			) {
		
		FlightSeat flSeat = flightSeatRepo.findOne(fs.getId());
		if(flSeat == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		if (flSeat.isReserved()) {
			return new ResponseEntity<>(new Message("Cannot delete reserved seat"), HttpStatus.BAD_REQUEST);
		}
		if (flSeat.isFastReservation()) {
			return new ResponseEntity<>(new Message("Cannot delete reserved seat for fast reservation"), HttpStatus.BAD_REQUEST);
		}
		flSeat.setDeleted(true);
		flSeat = flightSeatRepo.save(flSeat);
		return new ResponseEntity<>(flSeat, HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_AirlineAdmin')")
	@RequestMapping(
			value = "/admin-add",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> add(
			@RequestBody FlightSeat fs
			) {
		
		FlightSeat flSeat = flightSeatRepo.findOne(fs.getId());
		flSeat.setDeleted(false);
		flSeat = flightSeatRepo.save(flSeat);
		return new ResponseEntity<>(flSeat, HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_AirlineAdmin')")
	@RequestMapping(
			value = "/admin-add-fast-res",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> fastRes(
			@RequestBody FlightSeat fs
			) {
		
		FlightSeat flSeat = flightSeatRepo.findOne(fs.getId());
		if(flSeat == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		if (flSeat.isReserved()) {
			return new ResponseEntity<>(new Message("Cannot reserve reserved seat"), HttpStatus.BAD_REQUEST);
		}
		if (flSeat.isFastReservation()) {
			return new ResponseEntity<>(new Message("already added for fast reservation"), HttpStatus.BAD_REQUEST);
		}
		flSeat.setFastReservation(true);
		flSeat = flightSeatRepo.save(flSeat);
		return new ResponseEntity<>(flSeat, HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_AirlineAdmin')")
	@RequestMapping(
			value = "/admin-add-res",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> res(
			@RequestBody FlightSeat fs
			) {
		
		FlightSeat flSeat = flightSeatRepo.findOne(fs.getId());
		if(flSeat == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		if (flSeat.isReserved()) {
			return new ResponseEntity<>(new Message("Cannot reserve reserved seat"), HttpStatus.BAD_REQUEST);
		}
		if (flSeat.isFastReservation()) {
			return new ResponseEntity<>(new Message("Reserved for fast reservation"), HttpStatus.BAD_REQUEST);
		}
		flSeat.setReserved(true);
		flSeat = flightSeatRepo.save(flSeat);
		return new ResponseEntity<>(flSeat, HttpStatus.OK);
	}
	
}
