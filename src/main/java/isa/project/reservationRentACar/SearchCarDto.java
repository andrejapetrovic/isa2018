package isa.project.reservationRentACar;

import isa.project.car.CarType;

public class SearchCarDto {
	
	private String pickUpLocation;
	
	private String dropOfLocation;
	
	private String pickUpDateTime;
	
	private String dropOfDateTime;
	
	private int passengers;
	
	private CarType carType;
	
	private String modelNumber;

	public SearchCarDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchCarDto(String pickUpLocation, String dropOfLocation, String pickUpDateTime, String dropOfDateTime,
			int passengers, CarType carType, String modelNumber) {
		super();
		this.pickUpLocation = pickUpLocation;
		this.dropOfLocation = dropOfLocation;
		this.pickUpDateTime = pickUpDateTime;
		this.dropOfDateTime = dropOfDateTime;
		this.passengers = passengers;
		this.carType = carType;
		this.modelNumber = modelNumber;
	}

	public String getPickUpLocation() {
		return pickUpLocation;
	}

	public void setPickUpLocation(String pickUpLocation) {
		this.pickUpLocation = pickUpLocation;
	}

	public String getDropOfLocation() {
		return dropOfLocation;
	}

	public void setDropOfLocation(String dropOfLocation) {
		this.dropOfLocation = dropOfLocation;
	}

	public String getPickUpDateTime() {
		return pickUpDateTime;
	}

	public void setPickUpDateTime(String pickUpDateTime) {
		this.pickUpDateTime = pickUpDateTime;
	}

	public String getDropOfDateTime() {
		return dropOfDateTime;
	}

	public void setDropOfDateTime(String dropOfDateTime) {
		this.dropOfDateTime = dropOfDateTime;
	}

	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	

	



}
