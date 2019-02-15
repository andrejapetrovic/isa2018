package isa.project.flight.price;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import isa.project.cabin.FlightClass;
import isa.project.flight.Flight;

@Entity
public class FlightPrice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "price_id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private FlightClass flightClass;
	
	private double oneWayPrice;
	
	private double returnPrice;

	@ManyToOne
	@JoinColumn(name="flight_id", nullable=false)
	private Flight flight;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FlightClass getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(FlightClass flightClass) {
		this.flightClass = flightClass;
	}

	public double getOneWayPrice() {
		return oneWayPrice;
	}

	public void setOneWayPrice(double oneWayPrice) {
		this.oneWayPrice = oneWayPrice;
	}

	public double getReturnPrice() {
		return returnPrice;
	}

	public void setReturnPrice(double returnPrice) {
		this.returnPrice = returnPrice;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
}
