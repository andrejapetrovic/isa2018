package isa.project.cabin;

import java.util.List;

import isa.project.seat.Seat;

public class CabinDto {

private Long id;
	
	private String flightClass;

	private List<Seat> seats; 
	
	private Long aircraftId;
	
	private boolean configured;
	
	private int row, col, dx, dy, d;
	
	private String separations;
	
	private int separationSize;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public Long getAircraftId() {
		return aircraftId;
	}

	public void setAircraftId(Long aircraftId) {
		this.aircraftId = aircraftId;
	}

	public boolean isConfigured() {
		return configured;
	}

	public void setConfigured(boolean configured) {
		this.configured = configured;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public String getSeparations() {
		return separations;
	}

	public void setSeparations(String separations) {
		this.separations = separations;
	}

	public int getSeparationSize() {
		return separationSize;
	}

	public void setSeparationSize(int separationSize) {
		this.separationSize = separationSize;
	}
	
	
}
