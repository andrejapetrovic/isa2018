package isa.project.flight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.project.aircraft.Aircraft;
import isa.project.aircraft.AircraftRepository;
import isa.project.airline.Airline;
import isa.project.airline.AirlineRepository;
import isa.project.cabin.Cabin;
import isa.project.cabin.CabinRepository;
import isa.project.cabin.FlightClass;
import isa.project.destination.Destination;
import isa.project.destination.DestinationRepository;
import isa.project.flight.dto.FlightDto;
import isa.project.flight.dto.FlightFilterDto;
import isa.project.flight.dto.FlightReturnDto;
import isa.project.flight.dto.FlightSearchDto;
import isa.project.flight.dto.PriceDto;
import isa.project.flight.price.FlightPrice;
import isa.project.flight.price.FlightPriceRepository;
import isa.project.flight.seat.FlightSeat;
import isa.project.flight.seat.FlightSeatRepository;
import isa.project.seat.Seat;
import isa.project.user.Message;

@RestController
@RequestMapping(value="flight")
public class FlightController {

	@Autowired
	AircraftRepository airplaneRepo;
	
	@Autowired 
	DestinationRepository destRepo;	

	@Autowired
	AirlineRepository airlineRepo;
	
	@Autowired 
	FlightRepository flightRepo;
	
	@Autowired 
	FlightSeatRepository flightSeatRepo;
	
	@Autowired 
	CabinRepository fclassRepo;
	
	@Autowired
	FlightService flightService;
	
	@Autowired
	FlightPriceRepository priceRepo;
	
	@RequestMapping(
			value = "{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Flight> getFlight(@PathVariable("id") Long id) {
		Flight flight = flightRepo.findOne(id);
		if(flight != null)
			return new ResponseEntity<Flight>(flight, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasRole('ROLE_AirlineAdmin')")
	@RequestMapping(
			value = "add",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> add(@RequestBody FlightDto flightDto) throws Exception {
		Airline airline = airlineRepo.getOne(flightDto.getAirlineId());
		Aircraft plane = airplaneRepo.findOne(flightDto.getAircraftId());
		Flight flight = new Flight();
		
		SimpleDateFormat ft = new SimpleDateFormat ("dd-MMM-yyyy HH:mm");
		try {
			flight.setDepartureDate(ft.parse(flightDto.getDepartureDate()));
			flight.setLandingDate(ft.parse(flightDto.getLandingDate()));
		} catch (ParseException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new Message("Invalid date input"), HttpStatus.BAD_REQUEST);
		}
		
		Destination from = destRepo.findByAirportCode(flightDto.getFrom());
		Destination to = destRepo.findByAirportCode(flightDto.getTo());
		
		if (to.getId() == from.getId()) {
			return new ResponseEntity<>(new Message("You cannot have same departing and landing destination"), HttpStatus.BAD_REQUEST);
		}
		
		//validacija stop destinacija
		if(flightDto.getStopCount() > 0)  {
			for (String code : flightDto.getStopDestCodes()) {
				Destination stop = destRepo.findByAirportCode(code);
				if (stop == null) {
					return new ResponseEntity<>(new Message("Invalid stop destination input"), HttpStatus.BAD_REQUEST);
				}
				if (stop.getId() == to.getId() || stop.getId() == from.getId()) {
					return new ResponseEntity<>(new Message("You cannot have equal stop and departing or landing destination"), HttpStatus.BAD_REQUEST);
				}
			}
		}
		
		flight.setStopCount(flightDto.getStopCount());
		flight.setFrom(from);
		flight.setTo(to);
		if(flightDto.getStopCount() > 0) 
			flight.setStops(destRepo.findAllByCodes(flightDto.getStopDestCodes()));
		flight.setAirplane(plane);
		flight.setAirline(airline);
		flight.setOneWayPrice(flightDto.getOneWayPrice());
		flight.setReturnPrice(flightDto.getReturnPrice());
		flight.setTerminal1(flightDto.getTerminal1());
		flight.setTerminal2(flightDto.getTerminal2());
		airline.getFlights().add(flight);
		flight = flightRepo.save(flight);
		List<FlightSeat> flightSeats = new ArrayList<>();
		for (Cabin cabin : plane.getCabins()) {
			for (Seat seat : cabin.getSeats()) {
				FlightSeat flightSeat = new FlightSeat();
				flightSeat.setFlight(flight);
				flightSeat.setSeat(seat);
				flightSeats.add(flightSeat);
				flightSeat.setFlightClass(cabin.getFlightClass());
			}
		}
		flightSeatRepo.save(flightSeats);
		return new ResponseEntity<Flight>(flight, HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value = "get-by-airline/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Flight>> getFlightByAirline(@PathVariable("id") Long id) {
		List<Flight> flights = flightRepo.findByAirlineId(id);
		return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "search",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FlightReturnDto> search(
			@Valid FlightFilterDto filterDto, @Valid FlightSearchDto searchDto
			) throws Exception {
		List<List<Flight>> foundFlights = flightService.searchAndFilter(searchDto, filterDto);
		if(foundFlights == null) {
			return new ResponseEntity<FlightReturnDto>(HttpStatus.NOT_FOUND);
		}
		List<Long> ids = new ArrayList<>(); 
		for (List<Flight> list : foundFlights) {
			ids.addAll(list.stream()
					.map(Flight::getId)
					.collect(Collectors.toList()));
		}
		List<Destination> stops = destRepo.findStops(ids);
		List<Airline> airlines = airlineRepo.findByFlights(ids);
		FlightReturnDto retVal = new FlightReturnDto();
		retVal.setAirlines(airlines);
		retVal.setFlights(foundFlights);
		retVal.setStops(stops);
		return new ResponseEntity<FlightReturnDto>(retVal, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_AirlineAdmin')")
	@RequestMapping(
			value = "add-price/",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody PriceDto dto) throws Exception {
		Flight flight = flightRepo.getOne(dto.getFlightId());
		FlightPrice fp = new FlightPrice();
		fp.setFlightClass(FlightClass.valueOf(dto.getfClass()));
		fp.setOneWayPrice(dto.getOneWayPrice());
		fp.setReturnPrice(dto.getReturnPrice());
		fp.setFlight(flight);
		priceRepo.save(fp);
		flight.getPrice().add(fp);
		return new ResponseEntity<>(flightRepo.save(flight), HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "prices/{flightId}/{fclass}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPrices(@PathVariable("flightId") Long id, @PathVariable("fclass") FlightClass fclass) {
		return new ResponseEntity<>(priceRepo.findOneByFlightClassAndFlightId(fclass, id), HttpStatus.OK);
	}
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
}
