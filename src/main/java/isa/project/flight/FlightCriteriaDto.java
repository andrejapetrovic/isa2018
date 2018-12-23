package isa.project.flight;

import java.util.List;

import isa.project.flight.fclass.FlightClass;
import isa.project.flight.type.FlightType;

public class FlightCriteriaDto {

	private List<FlightType> types;
	
	private List<FlightClass> classes;

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
	
}
