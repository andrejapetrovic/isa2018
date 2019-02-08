package isa.project.reservationRentACar;

public class SearchRentDto {
	
	private String nameOrLoc;
	
	private String pickUpDate;
	
	private String dropOfDate;

	public SearchRentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchRentDto(String nameOrLoc, String pickUpDate, String dropOfDate) {
		super();
		this.nameOrLoc = nameOrLoc;
		this.pickUpDate = pickUpDate;
		this.dropOfDate = dropOfDate;
	}

	public String getNameOrLoc() {
		return nameOrLoc;
	}

	public void setNameOrLoc(String nameOrLoc) {
		this.nameOrLoc = nameOrLoc;
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
	
	
}
