package isa.project.flight.reservation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.project.flight.seat.FlightSeat;
import isa.project.flight.seat.FlightSeatRepository;
import isa.project.user.Message;

@RestController
@RequestMapping(value = "reservation")
public class ReservationController {

	@Autowired
	private ReservationRepository resRepo;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private FlightSeatRepository flightSeatRepository;
	
	@Autowired
	private FlightSeatRepository fsRepo;
	
	@PreAuthorize("hasRole('ROLE_RegisteredUser')")
	@RequestMapping(
			 value = "submit")
	public ResponseEntity<?> makeReservation(@Valid @RequestBody List<ReservationDto> resDtoList) {
		//validacija
		for (ReservationDto dto : resDtoList) {
			FlightSeat flightSeat = flightSeatRepository.findOne(dto.getFlightSeatId());
			if (flightSeat.isReserved()) {
				return new ResponseEntity<>(
						new Message("Seat with row " + flightSeat.getSeat().getRow() + " and column " 
								+ flightSeat.getSeat().getRow() + " has been reserved by someone else"),
						HttpStatus.BAD_REQUEST);
			}
			if (flightSeat.isDeleted()) {
				return new ResponseEntity<>(
						new Message("Seat with row " + flightSeat.getSeat().getRow() + " and column " 
								+ flightSeat.getSeat().getRow() + " has been deleted"),
						HttpStatus.BAD_REQUEST);
			}
			if(dto.getRetFlightSeatId() != -1) {
				FlightSeat fs = flightSeatRepository.findOne(dto.getRetFlightSeatId());
				if (fs.isReserved()) {
					return new ResponseEntity<>(
							new Message("Seat with row " + flightSeat.getSeat().getRow() + " and column " 
									+ flightSeat.getSeat().getRow() + " has been reserved by someone else"),
							HttpStatus.BAD_REQUEST);
				}
				if (fs.isDeleted()) {
					return new ResponseEntity<>(
							new Message("Seat with row " + flightSeat.getSeat().getRow() + " and column " 
									+ flightSeat.getSeat().getRow() + " has been deleted"),
							HttpStatus.BAD_REQUEST);
				}
			}
		}
		
		//dodavanje
		List<Reservation> resList = new ArrayList<>();
		resDtoList.forEach(resDto -> {
			Reservation newRes =  new Reservation();
			newRes.setName(resDto.getName());
			newRes.setLastName(resDto.getLastName());
			newRes.setDateOfBirth(resDto.getDateOfBirth());
			newRes.setEmail(resDto.getEmail());
			newRes.setPhone(resDto.getPhone());
			newRes.setPassportId(resDto.getPassportId());
			FlightSeat flightSeat = flightSeatRepository.findOne(resDto.getFlightSeatId());
			newRes.getFlightSeat().add(flightSeat);
			flightSeat.setReserved(true);
			if(resDto.getRetFlightSeatId() != -1) {
				FlightSeat fs = flightSeatRepository.findOne(resDto.getRetFlightSeatId());
				fs.setReserved(true);
				flightSeatRepository.save(fs);
				newRes.getFlightSeat().add(fs);
			}
			resList.add(newRes);
			emailService.sendEmail(newRes);
		});
		
		return new ResponseEntity<>(resRepo.save(resList), HttpStatus.CREATED);
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
	
	
	@RequestMapping(
			value = "get/fast",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getFast() {
		List<FlightSeat> res = fsRepo.findByFastReservationAndReserved(true, false);
		if(res == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
}
