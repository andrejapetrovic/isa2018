package isa.project.flight.dto;

import java.util.List;

public class FlightDto {

	private String from;
	
	private String to;
	
	private java.util.Date departDate;
	 
	private java.util.Date returnDate;

	private java.util.Date departTime;

	private java.util.Date returnTime;
	
	private int stopCount;

	private int airplaneModelNumber;
	
	private String airplaneModelName;
	
	private List<String> stopDestCodes;
	
	private Long airlineId;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public java.util.Date getDepartDate() {
		return departDate;
	}

	public void setDepartDate(java.util.Date departDate) {
		this.departDate = departDate;
	}

	public java.util.Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(java.util.Date returnDate) {
		this.returnDate = returnDate;
	}

	public java.util.Date getDepartTime() {
		return departTime;
	}

	public void setDepartTime(java.util.Date departTime) {
		this.departTime = departTime;
	}

	public java.util.Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(java.util.Date returnTime) {
		this.returnTime = returnTime;
	}

	public int getStopCount() {
		return stopCount;
	}

	public void setStopCount(int stopCount) {
		this.stopCount = stopCount;
	}

	public int getAirplaneModelNumber() {
		return airplaneModelNumber;
	}

	public void setAirplaneModelNumber(int airplaneModelNumber) {
		this.airplaneModelNumber = airplaneModelNumber;
	}

	public String getAirplaneModelName() {
		return airplaneModelName;
	}

	public void setAirplaneModelName(String airplaneModelName) {
		this.airplaneModelName = airplaneModelName;
	}

	public Long getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(Long airlineId) {
		this.airlineId = airlineId;
	}

	public List<String> getStopDestCodes() {
		return stopDestCodes;
	}

	public void setStopDestCodes(List<String> stopDestCodes) {
		this.stopDestCodes = stopDestCodes;
	}
	
}
