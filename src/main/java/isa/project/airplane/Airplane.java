package isa.project.airplane;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import isa.project.airline.Airline;
import isa.project.seat.Seat;
import isa.project.segment.Segment;

@Entity
public class Airplane {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "airplane_id")
	private Long id;
	
	private String modelName;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "airplane_segments", joinColumns = @JoinColumn(name = "airplane_id"), inverseJoinColumns = @JoinColumn(name = "segment_id"))
	@JsonIgnore
	private List<Segment> segments; 
	
	@ManyToOne
	@JoinColumn(name = "owner_airline_id")
	private Airline owner;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "airplane_seats", joinColumns = @JoinColumn(name = "airplane_id"), inverseJoinColumns = @JoinColumn(name = "seat_id"))
	private List<Seat> seats;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public List<Segment> getSegments() {
		return segments;
	}

	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}
	
	
	
}
