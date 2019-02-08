package isa.project.flight;

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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import isa.project.aircraft.Aircraft;
import isa.project.airline.Airline;
import isa.project.cabin.Cabin;
import isa.project.destination.Destination;

@Entity
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "flight_id")
	private Long id;

    @ManyToOne
    @JoinColumn(name="from_id")
	private Destination from;
	
    @ManyToOne
    @JoinColumn(name="to_id")
	private Destination to;
	
	@Basic
	@Column(columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private java.util.Date departureDate;
	 
	@Basic
	@Column(columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private java.util.Date landingDate;
	
	private int stopCount;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "flight_stops", joinColumns = @JoinColumn(name = "flight_id"), inverseJoinColumns = @JoinColumn(name = "dest_id"))
	private Set<Destination> stops;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "flight_cabins", joinColumns = @JoinColumn(name = "flight_id"), inverseJoinColumns = @JoinColumn(name = "cabin_id"))
	@JsonIgnore
	private Set<Cabin> cabin;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="aircraft_id")
	@NotNull
	private Aircraft airplane;
	
	@ManyToOne
	@JoinColumn(name = "airline_id")
	@NotNull
	private Airline airline;
	
	private double oneWayPrice;
	
	private double returnPrice;
	
	@NotNull
	private String terminal1;
	@NotNull
	private String terminal2;
	
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

	public Aircraft getAirplane() {
		return airplane;
	}

	public void setAirplane(Aircraft airplane) {
		this.airplane = airplane;
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

	public Set<Cabin> getClasses() {
		return cabin;
	}

	public void setClasses(Set<Cabin> classes) {
		this.cabin = classes;
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

	public java.util.Date getLandingDate() {
		return landingDate;
	}

	public void setLandingDate(java.util.Date landingDate) {
		this.landingDate = landingDate;
	}

	public Set<Cabin> getCabin() {
		return cabin;
	}

	public void setCabin(Set<Cabin> cabin) {
		this.cabin = cabin;
	}

	public String getTerminal1() {
		return terminal1;
	}

	public void setTerminal1(String terminal1) {
		this.terminal1 = terminal1;
	}

	public String getTerminal2() {
		return terminal2;
	}

	public void setTerminal2(String terminal2) {
		this.terminal2 = terminal2;
	}

	
}
