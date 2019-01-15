package isa.project.flight.reservation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isa.project.flight.dto.FlightType;
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
			resList.add(newRes);
		});
		
		//emailService.sendEmail(res);
		return new ResponseEntity<List<Reservation>>(resRepo.save(resList), HttpStatus.CREATED);
	}
	
}
