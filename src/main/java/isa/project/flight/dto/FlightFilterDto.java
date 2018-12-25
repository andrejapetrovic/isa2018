package isa.project.flight.dto;

import java.util.Date;
import java.util.List;

public class FlightFilterDto {

	private String fromDest;
	
	private String toDest;
	
	private Date departDate;
	
	private Date returnDate;
	
	private String ftype;
	
	private String fclass;
	
	private int passNum;

	private String priceRange;
	
	private int stops = -1;
	
	private Date takeoffTime1;
	
	private Date takeoffTime2;
	
	private Date landingTime1;
	
	private Date landingtime2;
	
	private Sort sort;
	
	private String airlines;
	
	private String stopDests;
	
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

	public String getFclass() {
		return fclass;
	}

	public void setFclass(String fclass) {
		this.fclass = fclass;
	}

	public String getFtype() {
		return ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	public int getPassNum() {
		return passNum;
	}

	public void setPassNum(int passNum) {
		this.passNum = passNum;
	}

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

	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	public int getStops() {
		return stops;
	}

	public void setStops(int stops) {
		this.stops = stops;
	}

	public Date getTakeoffTime1() {
		return takeoffTime1;
	}

	public void setTakeoffTime1(Date takeoffTime1) {
		this.takeoffTime1 = takeoffTime1;
	}

	public Date getTakeoffTime2() {
		return takeoffTime2;
	}

	public void setTakeoffTime2(Date takeoffTime2) {
		this.takeoffTime2 = takeoffTime2;
	}

	public Date getLandingTime1() {
		return landingTime1;
	}

	public void setLandingTime1(Date landingTime1) {
		this.landingTime1 = landingTime1;
	}

	public Date getLandingtime2() {
		return landingtime2;
	}

	public void setLandingtime2(Date landingtime2) {
		this.landingtime2 = landingtime2;
	}

	public String getAirlines() {
		return airlines;
	}

	public void setAirlines(String airlines) {
		this.airlines = airlines;
	}

	public String getStopDests() {
		return stopDests;
	}

	public void setStopDests(String stopDests) {
		this.stopDests = stopDests;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}
}

