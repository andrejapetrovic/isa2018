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
	
	private String terminal1;
	
	private String terminal2;
	
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

	public String getTerminal1() {
		return terminal1;
	}

	public void setTerminal1(String terminal1) {
		this.terminal1 = terminal1;
	}

	public String getTerminal2() {
		return terminal2;
	}

	public void setTerminal2(String terminal2) {
		this.terminal2 = terminal2;
	}


	
}
