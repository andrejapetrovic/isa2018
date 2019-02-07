package isa.project.flight.dto;

import java.util.List;

public class FlightDto {

	private String from;
	
	private String to;
	
	private String departureDate;
	 
	private String landingDate;
	
	private int stopCount;

	private List<String> stopDestCodes;
	
	private Long airlineId;
	
	private Long aircraftId;
	
	private int returnPrice, oneWayPrice;
	
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

	public int getStopCount() {
		return stopCount;
	}

	public void setStopCount(int stopCount) {
		this.stopCount = stopCount;
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

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getLandingDate() {
		return landingDate;
	}

	public void setLandingDate(String landingDate) {
		this.landingDate = landingDate;
	}

	public Long getAircraftId() {
		return aircraftId;
	}

	public void setAircraftId(Long aircraftId) {
		this.aircraftId = aircraftId;
	}

	public int getReturnPrice() {
		return returnPrice;
	}

	public void setReturnPrice(int returnPrice) {
		this.returnPrice = returnPrice;
	}

	public int getOneWayPrice() {
		return oneWayPrice;
	}

	public void setOneWayPrice(int oneWayPrice) {
		this.oneWayPrice = oneWayPrice;
	}


	
}
