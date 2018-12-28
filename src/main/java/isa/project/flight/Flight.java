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
	private java.util.Date departureDate;
	 
	@Basic
	@Temporal(TemporalType.TIME)
	private java.util.Date takeoffTime;
	 
	@Basic
	@Temporal(TemporalType.TIME)
	private java.util.Date landingTime;
	
	private int stopCount;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "flight_stops", joinColumns = @JoinColumn(name = "flight_id"), inverseJoinColumns = @JoinColumn(name = "dest_id"))
	private Set<Destination> stops;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "flight_classes", joinColumns = @JoinColumn(name = "flight_id"), inverseJoinColumns = @JoinColumn(name = "class_id"))
	private Set<FlightClass> classes;
	
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
	
	private double oneWayPrice;
	
	private double returnPrice;
	
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

	public int getRemainingSeats() {
		return remainingSeats;
	}

	public void setRemainingSeats(int remainingSeats) {
		this.remainingSeats = remainingSeats;
	}

	public double getOneWayPrice() {
		return oneWayPrice;
	}

	public void setOneWayPrice(double oneWayPrice) {
		this.oneWayPrice = oneWayPrice;
	}

	public double getReturnPrice() {
		return returnPrice;
	}

	public void setReturnPrice(double returnPrice) {
		this.returnPrice = returnPrice;
	}

	public java.util.Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(java.util.Date departureDate) {
		this.departureDate = departureDate;
	}

	public java.util.Date getTakeoffTime() {
		return takeoffTime;
	}

	public void setTakeoffTime(java.util.Date takeoffTime) {
		this.takeoffTime = takeoffTime;
	}

	public java.util.Date getLandingTime() {
		return landingTime;
	}

	public void setLandingTime(java.util.Date landingTime) {
		this.landingTime = landingTime;
	}
	
}
