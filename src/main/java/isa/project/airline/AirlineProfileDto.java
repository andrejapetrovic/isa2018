package isa.project.airline;

import java.util.List;

import isa.project.aircraft.Aircraft;
import isa.project.destination.Destination;
import isa.project.flight.Flight;

public class AirlineProfileDto {
	
	private Airline airline;
	
	private List<Flight> flights;
	
	private List<Destination> destinations;
	
	private List<Aircraft> aircrafts;

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public List<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(List<Destination> destinations) {
		this.destinations = destinations;
	}

	public List<Aircraft> getAircrafts() {
		return aircrafts;
	}

	public void setAircrafts(List<Aircraft> aircrafts) {
		this.aircrafts = aircrafts;
	}
	
	
}
