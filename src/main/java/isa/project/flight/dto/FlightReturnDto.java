package isa.project.flight.dto;

import java.util.List;

import isa.project.airline.Airline;
import isa.project.destination.Destination;
import isa.project.flight.Flight;

public class FlightReturnDto {

	private List<Flight> flights;
	
	private List<Airline> airlines;
	
	private List<Destination> stops;

	public List<Flight> getFlights() {
		return flights;
	}

	public List<Airline> getAirlines() {
		return airlines;
	}

	public void setAirlines(List<Airline> airlines) {
		this.airlines = airlines;
	}

	public List<Destination> getStops() {
		return stops;
	}

	public void setStops(List<Destination> stops) {
		this.stops = stops;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	
	
}
