package isa.project.flight.reservation;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import isa.project.flight.dto.FlightType;
import isa.project.flight.seat.FlightSeat;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "res_id")
	private Long id;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private String email;
	
	@NotNull
	private int phoneNum;
	
	@NotNull
	@Size(min=7, message="Passport should have atleast 7 characters")
	private int passportId;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "flight_reservation", joinColumns = @JoinColumn(name = "res_id"), inverseJoinColumns = @JoinColumn(name = "flight_id"))
	private Set<FlightSeat> flightSeat;
	
	private FlightType flightType;
	
	public int getPassportId() {
		return passportId;
	}
	
	public void setPassportId(int passportId) {
		this.passportId = passportId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public int getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Set<FlightSeat> getFlightSeat() {
		return flightSeat;
	}

	public void setFlightSeat(Set<FlightSeat> flightSeat) {
		this.flightSeat = flightSeat;
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
