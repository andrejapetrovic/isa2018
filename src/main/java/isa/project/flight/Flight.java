package isa.project.flight;

import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import isa.project.airline.Airline;
import isa.project.airplane.Airplane;
import isa.project.destination.Destination;
import isa.project.flight.fclass.FlightClass;
import isa.project.flight.type.FlightType;
import isa.project.seat.Seat;
import isa.project.segment.Segment;

@Entity
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "flight_id")
	private Long id;

    @OneToOne
    @JoinColumn(name="from_id")
	private Destination from;
	
    @OneToOne
    @JoinColumn(name="to_id")
	private Destination to;
	
	@Basic
	@Temporal(TemporalType.DATE)
	private java.util.Date departDate;
	 
	@Basic
	@Temporal(TemporalType.DATE)
	private java.util.Date returnDate;
	 
	@Basic
	@Temporal(TemporalType.TIME)
	private java.util.Date departTime;
	 
	@Basic
	@Temporal(TemporalType.TIME)
	private java.util.Date returnTime;
	
	private int stopCount;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "flight_stops", joinColumns = @JoinColumn(name = "flight_id"), inverseJoinColumns = @JoinColumn(name = "dest_id"))
	private Set<Destination> stops;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "flights_classes", joinColumns = @JoinColumn(name = "flight_id"), inverseJoinColumns = @JoinColumn(name = "class_id"))
	private Set<FlightClass> classes;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "flights_types", joinColumns = @JoinColumn(name = "flight_id"), inverseJoinColumns = @JoinColumn(name = "flight_type_id"))
	private Set<FlightType> types;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "flight_segments", joinColumns = @JoinColumn(name = "flight_id"), inverseJoinColumns = @JoinColumn(name = "segment_id"))
	@JsonIgnore
	private List<Segment> segments; 
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "flight_seats", joinColumns = @JoinColumn(name = "flight_id"), inverseJoinColumns = @JoinColumn(name = "seat_id"))
	@JsonIgnore
	private List<Seat> seats;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="airplane_id")
	private Airplane airplane;
	
	@ManyToOne
	@JoinColumn(name = "airline_id")
	private Airline airline;
	
	private int remainingSeats;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Destination getFrom() {
		return from;
	}

	public void setFrom(Destination from) {
		this.from = from;
	}

	public Destination getTo() {
		return to;
	}

	public void setTo(Destination to) {
		this.to = to;
	}

	public java.util.Date getDepartDate() {
		return departDate;
	}

	public void setDepartDate(java.util.Date departDate) {
		this.departDate = departDate;
	}


	public java.util.Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(java.util.Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getStopCount() {
		return stopCount;
	}

	public void setStopCount(int stopCount) {
		this.stopCount = stopCount;
	}

	public Set<Destination> getStops() {
		return stops;
	}

	public List<Segment> getSegments() {
		return segments;
	}

	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}

	public Airplane getAirplane() {
		return airplane;
	}

	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}

	public java.util.Date getDepartTime() {
		return departTime;
	}

	public void setDepartTime(java.util.Date departTime) {
		this.departTime = departTime;
	}

	public java.util.Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(java.util.Date returnTime) {
		this.returnTime = returnTime;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public void setStops(Set<Destination> stops) {
		this.stops = stops;
	}

	public Set<FlightClass> getClasses() {
		return classes;
	}

	public void setClasses(Set<FlightClass> classes) {
		this.classes = classes;
	}

	public Set<FlightType> getTypes() {
		return types;
	}

	public void setTypes(Set<FlightType> types) {
		this.types = types;
	}

	public int getRemainingSeats() {
		return remainingSeats;
	}

	public void setRemainingSeats(int remainingSeats) {
		this.remainingSeats = remainingSeats;
	}

	
}
