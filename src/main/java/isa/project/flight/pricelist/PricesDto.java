package isa.project.flight.pricelist;

import isa.project.airline.Airline;

public class PricesDto {

	private Long airlineId;
	
	private double carryOnBagFee;
	
	private double firstCheckedBagFee;
	
	private double secondCheckedBagFee;
	
	private double additionalBagFee;

	public Long getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(Long airlineId) {
		this.airlineId = airlineId;
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
