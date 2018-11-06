package isa.project.cabin;

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

import isa.project.flight.Flight;
import isa.project.flight.TravelClass;
import isa.project.segment.Segment;

@Entity
public class Cabin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cabin_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="flight_id")
	private Flight flight;

	private TravelClass travelClass;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "cabin_segments", joinColumns = @JoinColumn(name = "cabin_id"), inverseJoinColumns = @JoinColumn(name = "segment_id"))
	private List<Segment> segments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public TravelClass getTravelClass() {
		return travelClass;
	}

	public void setTravelClass(TravelClass travelClass) {
		this.travelClass = travelClass;
	}

	public List<Segment> getSegments() {
		return segments;
	}

	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}
	
	
}
