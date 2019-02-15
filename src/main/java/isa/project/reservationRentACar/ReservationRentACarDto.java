package isa.project.reservationRentACar;

import isa.project.car.CarType;

public class ReservationRentACarDto {
	
	private String pickUpLoc;
	
	private String dropOfLoc;
	
	private String pickUpDate;
	
	private String dropOfDate;
	
	private int passengers;
	
	private CarType carType;
	
	private int modelNumber;
	
	private long userId;

	public ReservationRentACarDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservationRentACarDto(String pickUpLoc, String dropOfLoc, String pickUpDate, String dropOfDate,
			int passengers, CarType carType, int modelNumber) {
		super();
		this.pickUpLoc = pickUpLoc;
		this.dropOfLoc = dropOfLoc;
		this.pickUpDate = pickUpDate;
		this.dropOfDate = dropOfDate;
		this.passengers = passengers;
		this.carType = carType;
		this.modelNumber = modelNumber;
	}

	public String getPickUpLoc() {
		return pickUpLoc;
	}

	public void setPickUpLoc(String pickUpLoc) {
		this.pickUpLoc = pickUpLoc;
	}

	public String getDropOfLoc() {
		return dropOfLoc;
	}

	public void setDropOfLoc(String dropOfLoc) {
		this.dropOfLoc = dropOfLoc;
	}

	public String getPickUpDate() {
		return pickUpDate;
	}

	public void setPickUpDate(String pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	public String getDropOfDate() {
		return dropOfDate;
	}

	public void setDropOfDate(String dropOfDate) {
		this.dropOfDate = dropOfDate;
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

	public int getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(int modelNumber) {
		this.modelNumber = modelNumber;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	
	

}
