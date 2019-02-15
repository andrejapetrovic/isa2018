package isa.project.flight.dto;

import java.util.List;

import isa.project.airline.Airline;
import isa.project.destination.Destination;
import isa.project.flight.Flight;
import isa.project.flight.price.FlightPrice;

public class FlightReturnDto {

	private List<List<FlightPrice>> flights;
	
	private List<Airline> airlines;
	
	private List<Destination> stops;

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

	public List<List<FlightPrice>> getFlights() {
		return flights;
	}

	public void setFlights(List<List<FlightPrice>> flights) {
		this.flights = flights;
	}

}
