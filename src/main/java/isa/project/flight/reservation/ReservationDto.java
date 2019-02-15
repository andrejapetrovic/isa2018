package isa.project.flight.reservation;

import isa.project.flight.dto.FlightType;

public class ReservationDto {

	private Long flightSeatId;
	
	private Long retFlightSeatId = new Long(-1);
	
	private String name;
	
	private String lastName;
	
	private String email;
	
	private int phone;
	
	private java.util.Date dateOfBirth;
	
	private int passportId;
	
	private double oneWayPrice;
	
	private double returnPrice;
	
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

	public Long getFlightSeatId() {
		return flightSeatId;
	}

	public void setFlightSeatId(Long flightSeatId) {
		this.flightSeatId = flightSeatId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public java.util.Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(java.util.Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getPassportId() {
		return passportId;
	}

	public void setPassportId(int passportId) {
		this.passportId = passportId;
	}

	public Long getRetFlightSeatId() {
		return retFlightSeatId;
	}

	public void setRetFlightSeatId(Long retFlightSeatId) {
		this.retFlightSeatId = retFlightSeatId;
	}
	
}
