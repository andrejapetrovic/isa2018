package isa.project.flight.dto;

import java.util.Date;

public class FlightSearchDto {

	private String fromDestCode;
	
	private String toDestCode;
	
	private Date departDate;
	
	private Date returnDate;
	
	private String type;
	
	private String fclass;
	
	private int numOfPassengers;

	public String getFromDestCode() {
		return fromDestCode;
	}

	public void setFromDestCode(String fromDestCode) {
		this.fromDestCode = fromDestCode;
	}

	public String getToDestCode() {
		return toDestCode;
	}

	public void setToDestCode(String toDestCode) {
		this.toDestCode = toDestCode;
	}

	public Date getDepartDate() {
		return departDate;
	}

	public void setDepartDate(Date departDate) {
		this.departDate = departDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFclass() {
		return fclass;
	}

	public void setFclass(String fclass) {
		this.fclass = fclass;
	}

	public int getNumOfPassengers() {
		return numOfPassengers;
	}

	public void setNumOfPassengers(int numOfPassengers) {
		this.numOfPassengers = numOfPassengers;
	}
	
	
}
