package isa.project.airline;

import java.io.Serializable;
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

import isa.project.airplane.Airplane;
import isa.project.destination.Destination;
import isa.project.flight.Flight;
import isa.project.flight.pricelist.PriceList;
@Entity
public class Airline implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7279009747372791242L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "airline_id")
	private Long id;
	
	private String name;
	
	private String address;
	
	private String description;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "airline_destinations", joinColumns = @JoinColumn(name = "airline_id"), inverseJoinColumns = @JoinColumn(name = "dest_id"))
	private List<Destination> destinations;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "airline_flights", joinColumns = @JoinColumn(name = "airline_id"), inverseJoinColumns = @JoinColumn(name = "flight_id"))
	private List<Flight> flights; 
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "airline_planes", joinColumns = @JoinColumn(name = "airline_id"), inverseJoinColumns = @JoinColumn(name = "airplane_id"))
	private List<Airplane> planes; 
	
	@OneToMany(mappedBy="flight")
	private List<PriceList> pricelist;
	
	public Airline() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(List<Destination> destinations) {
		this.destinations = destinations;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public List<Airplane> getPlanes() {
		return planes;
	}

	public void setPlanes(List<Airplane> planes) {
		this.planes = planes;
	}

	public List<PriceList> getPricelist() {
		return pricelist;
	}

	public void setPricelist(List<PriceList> pricelist) {
		this.pricelist = pricelist;
	}


	

}

