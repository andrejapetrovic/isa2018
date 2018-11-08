package isa.project.flight;

import java.util.List;

public class FlightDto {

	private Long startingDestId;
	
	private Long endingDestId;
	
	private java.util.Date departDate;
	 
	private java.util.Date returnDate;
	 
	private int stopCount;
	
	private List<Long> stopIds;

	private List<Long> segmentsId;
	
	private Long airplaneId;
	
	public Long getStartingDestId() {
		return startingDestId;
	}

	public void setStartingDestId(Long startingDestId) {
		this.startingDestId = startingDestId;
	}

	public Long getEndingDestId() {
		return endingDestId;
	}

	public void setEndingDestId(Long endingDestId) {
		this.endingDestId = endingDestId;
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

	public int getStopCount() {
		return stopCount;
	}

	public void setStopCount(int stopCount) {
		this.stopCount = stopCount;
	}

	public List<Long> getStopIds() {
		return stopIds;
	}

	public void setStopIds(List<Long> stopIds) {
		this.stopIds = stopIds;
	}

	public Long getAirplaneId() {
		return airplaneId;
	}

	public void setAirplaneId(Long airplaneId) {
		this.airplaneId = airplaneId;
	}

	public List<Long> getSegmentsId() {
		return segmentsId;
	}

	public void setSegmentsId(List<Long> segmentsId) {
		this.segmentsId = segmentsId;
	}
	
	
}
