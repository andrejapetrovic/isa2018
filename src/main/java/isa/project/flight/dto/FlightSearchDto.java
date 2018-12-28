package isa.project.flight.dto;

import java.util.Date;

public class FlightSearchDto {

	private String fromDest;
	
	private String toDest;
	
	private Date departDate;
	
	private Date returnDate;

	public String getFromDest() {
		return fromDest;
	}

	public void setFromDest(String fromDest) {
		this.fromDest = fromDest;
	}

	public String getToDest() {
		return toDest;
	}

	public void setToDest(String toDest) {
		this.toDest = toDest;
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

	
}
