package isa.project.airline;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import isa.project.airplane.Airplane;
import isa.project.destination.Destination;
import isa.project.flight.Flight;
import isa.project.flight.pricelist.PriceList;

public class AirlineDto {

private String name;
	
	private String address;
	
	private String description;
	
	private List<Long> destinationIds;
	
	private List<Long> flightIds; 
	
	private List<Long> planeIds; 
	
	private List<Long> pricelistIds;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Long> getDestinationIds() {
		return destinationIds;
	}

	public void setDestinationIds(List<Long> destinationIds) {
		this.destinationIds = destinationIds;
	}

	public List<Long> getFlightIds() {
		return flightIds;
	}

	public void setFlightIds(List<Long> flightIds) {
		this.flightIds = flightIds;
	}

	public List<Long> getPlaneIds() {
		return planeIds;
	}

	public void setPlaneIds(List<Long> planeIds) {
		this.planeIds = planeIds;
	}

	public List<Long> getPricelistIds() {
		return pricelistIds;
	}

	public void setPricelistIds(List<Long> pricelistIds) {
		this.pricelistIds = pricelistIds;
	}
	
	
	
}
