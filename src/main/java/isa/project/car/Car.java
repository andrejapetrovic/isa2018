package isa.project.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import isa.project.branchOffice.BranchOffice;


@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private int modelNumber;
	
	@Column(nullable = false)
	private String modelName;
	
	private int numberOfSeats;
	
	private int numberOfCases;
	
	private int numberOfDoors;
	
	@Column(nullable = false)
	private double pricePerDay;
	
	private String description;
	
	@Enumerated(EnumType.STRING)
	private CarType carType;

	@ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "branchOffice_id")
	private BranchOffice branchOffice;
	
	private boolean reserved;
	 
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Car(Long id, int modelNumber, String modelName, int numberOfSeats, int numberOfCases, int numberOfDoors,
			double pricePerDay, String description, CarType carType, BranchOffice branchOffice, boolean reserved) {
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
		this.branchOffice = branchOffice;
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

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	public BranchOffice getBranchOffice() {
		return branchOffice;
	}

	public void setBranchOffice(BranchOffice branchOffice) {
		this.branchOffice = branchOffice;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}


}
