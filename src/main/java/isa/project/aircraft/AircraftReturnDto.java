package isa.project.aircraft;

import java.util.List;
import java.util.Map;

import isa.project.cabin.Cabin;
import isa.project.seat.Seat;

public class AircraftReturnDto {

	private Aircraft aircraft;
	
	private List<Cabin> cabins;
	
	private List<Seat> seats;

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	public List<Cabin> getCabins() {
		return cabins;
	}

	public void setCabins(List<Cabin> cabins) {
		this.cabins = cabins;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	
	

}
