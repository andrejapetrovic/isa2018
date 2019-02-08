package isa.project.cabin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.project.aircraft.Aircraft;
import isa.project.aircraft.AircraftRepository;
import isa.project.flight.Flight;
import isa.project.flight.FlightRepository;
import isa.project.flight.seat.FlightSeat;
import isa.project.flight.seat.FlightSeatRepository;
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
	
	@Autowired
	private FlightSeatRepository fsRepo;
	
	@Autowired
	private FlightRepository flightRepo;	
	
	@PreAuthorize("hasRole('ROLE_AirlineAdmin')")
	@RequestMapping(
			value = "add",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cabin> add(@RequestBody CabinDto dto) throws Exception {
		Cabin cab = new Cabin();
		Aircraft aircraft = aircraftRepo.findOne(dto.getAircraftId());
		cab.setAircraft(aircraft);
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
		
		List<Flight> flights = flightRepo.findByAirplaneId(aircraft.getId());
		List<FlightSeat> flightSeats = new ArrayList<>();
		
		dto.getSeats().forEach(seat -> {
			seat.setCabin(cabin);
			flights.forEach(flight -> {
				FlightSeat newFs = new FlightSeat();
				newFs.setFlight(flight);
				newFs.setSeat(seat);
				newFs.setFlightClass(seat.getCabin().getFlightClass());
				flightSeats.add(newFs);
			});
		});
		
		seatRepo.save(dto.getSeats());
		if(!flightSeats.isEmpty())
			fsRepo.save(flightSeats);
		return new ResponseEntity<>(cabin, HttpStatus.CREATED);
	}
	
}
