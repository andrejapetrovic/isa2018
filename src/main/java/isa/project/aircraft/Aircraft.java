package isa.project.aircraft;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import isa.project.airline.Airline;
import isa.project.cabin.Cabin;

@Entity
public class Aircraft {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "aircraft_id")
	private Long id;
	
	private String modelName;
	
	@Column(unique = true)
	private int modelNumber;
	
	@OneToMany(mappedBy="aircraft", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Cabin> cabins; 
	
	@ManyToOne
	@JoinColumn(name = "owner_airline_id")
	private Airline owner;
	
	@Enumerated(EnumType.STRING)
	private AircraftType type;
	
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

	public Airline getOwner() {
		return owner;
	}

	public void setOwner(Airline owner) {
		this.owner = owner;
	}

	public int getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(int modelNumber) {
		this.modelNumber = modelNumber;
	}

	public List<Cabin> getCabins() {
		return cabins;
	}

	public AircraftType getType() {
		return type;
	}

	public void setType(AircraftType type) {
		this.type = type;
	}

	public void setCabins(List<Cabin> cabins) {
		this.cabins = cabins;
	}

}
