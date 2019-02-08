package isa.project.car;

import isa.project.branchOffice.BranchOfficeDto;

public class CarDto {

	private Long id;
	
	private int modelNumber;
	
	private String modelName;
	
	private int numberOfSeats;
	
	private int numberOfCases;
	
	private int numberOfDoors;
	
	private double pricePerDay;
	
	private String description;
	
	private String carType;
	
	private boolean reserved;
	
	public CarDto() {
		
	}

	public CarDto(Long id, int modelNumber, String modelName, int numberOfSeats, int numberOfCases, int numberOfDoors,
			double pricePerDay, String description, String carType, boolean reserved) {
		super();
		this.id = id;
		this.modelNumber = modelNumber;
		this.modelName = modelName;
		this.numberOfSeats = numberOfSeats;
		this.numberOfCases = numberOfCases;
		this.numberOfDoors = numberOfDoors;
		this.pricePerDay = pricePerDay;
		this.description = description;
		this.carType = carType;
		this.reserved = reserved;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(int modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public int getNumberOfCases() {
		return numberOfCases;
	}

	public void setNumberOfCases(int numberOfCases) {
		this.numberOfCases = numberOfCases;
	}

	public int getNumberOfDoors() {
		return numberOfDoors;
	}

	public void setNumberOfDoors(int numberOfDoors) {
		this.numberOfDoors = numberOfDoors;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}
}
