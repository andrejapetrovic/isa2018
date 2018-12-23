package isa.project.flight.dto;

import java.util.List;

import isa.project.flight.fclass.FlightClass;
import isa.project.flight.passenger.Passenger;
import isa.project.flight.type.FlightType;

public class FlightCriteriaDto {

	private List<FlightType> types;
	
	private List<FlightClass> classes;
	
	private List<Passenger> passengers;

	public List<FlightType> getTypes() {
		return types;
	}

	public void setTypes(List<FlightType> types) {
		this.types = types;
	}

	public List<FlightClass> getClasses() {
		return classes;
	}

	public void setClasses(List<FlightClass> classes) {
		this.classes = classes;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	
	
	
}
