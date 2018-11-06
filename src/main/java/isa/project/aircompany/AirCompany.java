package isa.project.aircompany;

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

import isa.project.destination.Destination;
import isa.project.flight.Flight;
import isa.project.flight.pricelist.PriceList;
@Entity
public class AirCompany implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7279009747372791242L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "airCom_id")
	private Long id;
	
	private String name;
	
	private String address;
	
	private String description;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "airCom_destinations", joinColumns = @JoinColumn(name = "airCom_id"), inverseJoinColumns = @JoinColumn(name = "dest_id"))
	private List<Destination> destinations;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "airCom_flights", joinColumns = @JoinColumn(name = "airCom_id"), inverseJoinColumns = @JoinColumn(name = "flight_id"))
	private List<Flight> flights; 
	
	@OneToMany(mappedBy="flight")
	private List<PriceList> pricelist;
	
	public AirCompany() {
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


	

}

