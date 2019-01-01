package isa.project.cabin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.project.aircraft.AircraftRepository;
import isa.project.seat.SeatRepository;

@RestController
@RequestMapping(value = "cabin")
public class CabinController {

	@Autowired
	private CabinRepository cabinRepo;
	
	@Autowired
	private AircraftRepository aircraftRepo;
	
	@Autowired
	private SeatRepository seatRepo;
	
	@RequestMapping(
			value = "add",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cabin> add(@RequestBody CabinDto dto) throws Exception {
		Cabin cab = new Cabin();
		cab.setAircraft(aircraftRepo.findOne(dto.getAircraftId()));
		cab.setRow(dto.getRow());
		cab.setCol(dto.getCol());
		cab.setDx(dto.getDx());
		cab.setDy(dto.getDy());
		cab.setD(dto.getD());
		cab.setSeparations(dto.getSeparations());
		cab.setSeparationSize(dto.getSeparationSize());
		cab.setConfigured(dto.isConfigured());
		FlightClass fclass = FlightClass.valueOf(StringUtils.capitalize(dto.getFlightClass()));
		cab.setFlightClass(fclass);
		Cabin cabin = cabinRepo.save(cab);
		dto.getSeats().forEach(seat -> {
			seat.setCabin(cabin);
		});
		seatRepo.save(dto.getSeats());
		return new ResponseEntity<>(cabin, HttpStatus.CREATED);
	}
	
}
