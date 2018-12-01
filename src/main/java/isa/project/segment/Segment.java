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

	private int rowCount, colCount;
	
	@Enumerated(EnumType.STRING)
	private Cabin travelClass;
	
	public Long getId() {
		return id;
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
