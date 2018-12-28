package isa.project.cabin;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import isa.project.aircraft.Aircraft;
import isa.project.seat.Seat;

@Entity
public class Cabin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cabin_id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private FlightClass flight_class;

	@OneToMany(mappedBy="cabin", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Seat> seats; 
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "aircraft_id")
	private Aircraft aircraft;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FlightClass getFlight_class() {
		return flight_class;
	}

	public void setFlight_class(FlightClass flight_class) {
		this.flight_class = flight_class;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

}
