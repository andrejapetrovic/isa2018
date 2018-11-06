package isa.project.flight;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import isa.project.cabin.Cabin;
import isa.project.destination.Destination;

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
	@Temporal(TemporalType.TIME)
	private java.util.Date departTime;
	
	@Basic
	@Temporal(TemporalType.DATE)
	private java.util.Date returnDate;
	 
	@Basic
	@Temporal(TemporalType.TIME)
	private java.util.Date returnTime;
	
	private int stopCount;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "flight_stops", joinColumns = @JoinColumn(name = "flight_id"), inverseJoinColumns = @JoinColumn(name = "dest_id"))
	private List<Destination> stops;

	@ElementCollection(targetClass = TravelClass.class)
    @Enumerated(EnumType.STRING) 
    @CollectionTable(name="flight_classes",  joinColumns = @JoinColumn(name = "flight_id"))
    @Column(name="classes")
	private List<TravelClass> travelClasses;
	
	private String ticketPrice;
	
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

	public java.util.Date getDepartTime() {
		return departTime;
	}

	public void setDepartTime(java.util.Date departTime) {
		this.departTime = departTime;
	}

	public java.util.Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(java.util.Date returnDate) {
		this.returnDate = returnDate;
	}

	public java.util.Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(java.util.Date returnTime) {
		this.returnTime = returnTime;
	}

	public int getStopCount() {
		return stopCount;
	}

	public void setStopCount(int stopCount) {
		this.stopCount = stopCount;
	}

	public List<Destination> getStops() {
		return stops;
	}

	public void setStops(List<Destination> stops) {
		this.stops = stops;
	}

	public List<TravelClass> getTravelClasses() {
		return travelClasses;
	}

	public void setTravelClasses(List<TravelClass> travelClasses) {
		this.travelClasses = travelClasses;
	}

	public String getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
	
	
}
