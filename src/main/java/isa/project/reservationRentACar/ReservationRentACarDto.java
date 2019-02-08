package isa.project.reservationRentACar;

public class ReservationRentACarDto {
	
	private String pickUpLoc;
	
	private String dropOfLoc;
	
	private String pickUpDate;
	
	private String dropOfDate;
	
	private int passengers;
	
	private String carType;

	public ReservationRentACarDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservationRentACarDto(String pickUpLoc, String dropOfLoc, String pickUpDate, String dropOfDate,
			int passengers, String carType) {
		super();
		this.pickUpLoc = pickUpLoc;
		this.dropOfLoc = dropOfLoc;
		this.pickUpDate = pickUpDate;
		this.dropOfDate = dropOfDate;
		this.passengers = passengers;
		this.carType = carType;
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

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

}
