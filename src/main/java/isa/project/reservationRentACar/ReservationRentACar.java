package isa.project.reservationRentACar;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import isa.project.branchOffice.BranchOffice;
import isa.project.car.Car;
import isa.project.car.CarType;
import isa.project.rentACar.RentACar;

@Entity
public class ReservationRentACar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "reservation_id")
	private Long id;

	private String pickUpLoc;
	
	private String dropOfLoc;
	
	@Basic
	@Column(columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date pickUpDate;
	 
	@Basic
	@Column(columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dropOfDate;
	
	private int passengers;
	
	@Enumerated(EnumType.STRING)
	private CarType carType;
	
	@ManyToOne
    @JoinColumn(name = "servis_id")
	private RentACar servis;
	
	@ManyToOne
    @JoinColumn(name = "car_id")
	private Car car;

	public ReservationRentACar() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservationRentACar(Long id, String pickUpLoc, String dropOfLoc, Date pickUpDate, Date dropOfDate,
			int passengers, CarType carType, RentACar servis, Car car) {
		super();
		this.id = id;
		this.pickUpLoc = pickUpLoc;
		this.dropOfLoc = dropOfLoc;
		this.pickUpDate = pickUpDate;
		this.dropOfDate = dropOfDate;
		this.passengers = passengers;
		this.carType = carType;
		this.servis = servis;
		this.car = car;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public java.util.Date getPickUpDate() {
		return pickUpDate;
	}

	public void setPickUpDate(java.util.Date pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	public java.util.Date getDropOfDate() {
		return dropOfDate;
	}

	public void setDropOfDate(java.util.Date dropOfDate) {
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

	public RentACar getServis() {
		return servis;
	}

	public void setServis(RentACar servis) {
		this.servis = servis;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	
}
