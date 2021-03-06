package isa.project.flight.pricelist;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import isa.project.airline.Airline;

@Entity
public class PriceList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pricelist_id")
	private Long id;
	
	private double carryOnBagFee;
	
	private double firstCheckedBagFee;
	
	private double secondCheckedBagFee;
	
	private double additionalBagFee;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getCarryOnBagFee() {
		return carryOnBagFee;
	}

	public void setCarryOnBagFee(double carryOnBagFee) {
		this.carryOnBagFee = carryOnBagFee;
	}

	public double getFirstCheckedBagFee() {
		return firstCheckedBagFee;
	}

	public void setFirstCheckedBagFee(double firstCheckedBagFee) {
		this.firstCheckedBagFee = firstCheckedBagFee;
	}

	public double getSecondCheckedBagFee() {
		return secondCheckedBagFee;
	}

	public void setSecondCheckedBagFee(double secondCheckedBagFee) {
		this.secondCheckedBagFee = secondCheckedBagFee;
	}

	public double getAdditionalBagFee() {
		return additionalBagFee;
	}

	public void setAdditionalBagFee(double additionalBagFee) {
		this.additionalBagFee = additionalBagFee;
	}

	
}
