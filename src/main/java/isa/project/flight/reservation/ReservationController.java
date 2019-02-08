package isa.project.flight.reservation;

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
import isa.project.destination.Destination;
import isa.project.flight.Flight;
import isa.project.flight.dto.FlightReturnDto;
import isa.project.flight.seat.FlightSeatRepository;

@RestController
@RequestMapping(value = "reservation")
public class ReservationController {

	@Autowired
	private ReservationRepository resRepo;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private FlightSeatRepository flightSeatRepository;
	
	@RequestMapping(
			 value = "submit")
	public ResponseEntity<?> makeReservation(@Valid @RequestBody List<ReservationDto> resDtoList) {
		List<Reservation> resList = new ArrayList<>();
		resDtoList.forEach(resDto -> {
			Reservation newRes =  new Reservation();
			newRes.setName(resDto.getName());
			newRes.setLastName(resDto.getLastName());
			newRes.setDateOfBirth(resDto.getDateOfBirth());
			newRes.setEmail(resDto.getEmail());
			newRes.setPhone(resDto.getPhone());
			newRes.setPassportId(resDto.getPassportId());
			newRes.getFlightSeat().add(flightSeatRepository.findOne(resDto.getFlightSeatId()));
			if(resDto.getRetFlightSeatId() != -1) {
				newRes.getFlightSeat().add(flightSeatRepository.findOne(resDto.getRetFlightSeatId()));
			}
			resList.add(newRes);
		});
		
		//emailService.sendEmail(res);
		return new ResponseEntity<List<Reservation>>(resRepo.save(resList), HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value = "get/{email}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> search(@PathVariable("email") String email) throws Exception {
		logger.info(email);
		List<Reservation> res = resRepo.findByEmail(email+".com");
		if(res == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	private Logger logger = LoggerFactory.getLogger(this.getClass());
}
