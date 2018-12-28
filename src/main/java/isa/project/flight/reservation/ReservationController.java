package isa.project.flight.reservation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "reservation")
public class ReservationController {

	@Autowired
	private ReservationRepository resRepo;
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(
			 value = "submit")
	public ResponseEntity<?> makeReservation(@Valid @RequestBody Reservation res) {
		res = resRepo.save(res);
		emailService.sendEmail(res);
		return new ResponseEntity<Reservation>(res, HttpStatus.CREATED);
	}
	
}
