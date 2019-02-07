package isa.project.airline;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import isa.project.aircraft.Aircraft;
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
	
	@Column(unique = true)
	private String name;
	
	@Column(unique = true)
	private String address;
	
	private String description;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "airline_destinations", joinColumns = @JoinColumn(name = "airline_id"), inverseJoinColumns = @JoinColumn(name = "dest_id"))
	@JsonIgnore
	private Set<Destination> destinations;
	
	@OneToMany(mappedBy="airline", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Flight> flights; 
	
	@OneToMany(mappedBy="owner", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Aircraft> planes; 
	
	@OneToOne
	private PriceList pricelist;
	
	public Airline() {
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

	public Set<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(Set<Destination> destinations) {
		this.destinations = destinations;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public List<Aircraft> getPlanes() {
		return planes;
	}

	public void setPlanes(List<Aircraft> planes) {
		this.planes = planes;
	}
	
	public PriceList getPricelist() {
		return pricelist;
	}

	public void setPricelist(PriceList pricelist) {
		this.pricelist = pricelist;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

