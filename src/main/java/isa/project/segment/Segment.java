package isa.project.segment;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import isa.project.flight.Cabin;
import isa.project.seat.Seat;

@Entity
public class Segment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "segment_id")
	private Long id;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "segment_seats", joinColumns = @JoinColumn(name = "segment_id"), inverseJoinColumns = @JoinColumn(name = "seat_id"))
	private List<Seat> seats;
	
	private int rowCount, colCount;
	
	private Cabin travelClass;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getColCount() {
		return colCount;
	}

	public void setColCount(int colCount) {
		this.colCount = colCount;
	}

	public Cabin getTravelClass() {
		return travelClass;
	}

	public void setTravelClass(Cabin travelClass) {
		this.travelClass = travelClass;
	}
	
	
}
