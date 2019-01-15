package isa.project.flight.seat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
}
