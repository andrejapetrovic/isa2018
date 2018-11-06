package isa.project.flight.pricelist;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import isa.project.flight.Flight;

@Entity
public class PriceList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pricelist_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="flight_id")
	private Flight flight;
	
	private String carryOnBagPrice;
	
	private String checkedBagPrice;
	
	private int carryOnBagCount;
	
	private int checkedBagCount;

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

	public String getCarryOnBagPrice() {
		return carryOnBagPrice;
	}

	public void setCarryOnBagPrice(String carryOnBagPrice) {
		this.carryOnBagPrice = carryOnBagPrice;
	}

	public String getCheckedBagPrice() {
		return checkedBagPrice;
	}

	public void setCheckedBagPrice(String checkedBagPrice) {
		this.checkedBagPrice = checkedBagPrice;
	}

	public int getCarryOnBagCount() {
		return carryOnBagCount;
	}

	public void setCarryOnBagCount(int carryOnBagCount) {
		this.carryOnBagCount = carryOnBagCount;
	}

	public int getCheckedBagCount() {
		return checkedBagCount;
	}

	public void setCheckedBagCount(int checkedBagCount) {
		this.checkedBagCount = checkedBagCount;
	}
	
	
}
