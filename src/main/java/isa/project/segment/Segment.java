package isa.project.segment;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import isa.project.airplane.Airplane;
import isa.project.flight.Cabin;
import isa.project.seat.Seat;

@Entity
public class Segment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "segment_id")
	private Long id;

	private int rows, cols;
	
	@Enumerated(EnumType.STRING)
	private Cabin travelClass;
	

	public Long getId() {
		return id;
	}

	public Cabin getTravelClass() {
		return travelClass;
	}

	public void setTravelClass(Cabin travelClass) {
		this.travelClass = travelClass;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
