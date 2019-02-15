package isa.project.flight.reservation;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import isa.project.flight.dto.FlightType;
import isa.project.flight.seat.FlightSeat;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "res_id")
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private String email;
	
	@NotNull
	private int phone;
	
	@Basic
	@Column(columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dateOfBirth;
	
	@NotNull
	private int passportId;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "flight_reservation", joinColumns = @JoinColumn(name = "res_id"), inverseJoinColumns = @JoinColumn(name = "flight_seat_id"))
	private Set<FlightSeat> flightSeat = new HashSet<>();
	
	private FlightType flightType;
	
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

	public int getPassportId() {
		return passportId;
	}
	
	public void setPassportId(int passportId) {
		this.passportId = passportId;
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

	public Set<FlightSeat> getFlightSeat() {
		return flightSeat;
	}

	public void setFlightSeat(Set<FlightSeat> flightSeat) {
		this.flightSeat = flightSeat;
	}

	public java.util.Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(java.util.Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FlightType getFlightType() {
		return flightType;
	}

	public void setFlightType(FlightType flightType) {
		this.flightType = flightType;
	}
	
}
