package isa.project.flight.seat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import isa.project.flight.Flight;
import isa.project.seat.Seat;

@Entity
public class FlightSeat {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "flight_id")
	private Flight flight;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "seat_id")
	private Seat seat;
	
	private boolean reserved = false;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
