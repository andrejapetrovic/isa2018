package isa.project.rentACar;

import isa.project.branchOffice.BranchOfficeDto;

public class RentACarDto {
	
	private Long id;
	
	private String nameOfRentACar;
	
	private String address;
	
	private String description;
	
	private int rating;
	
	public RentACarDto() {
		
	}

	public RentACarDto(Long id, String nameOfRentACar, String address, String description, int rating) {
		super();
		this.id = id;
		this.nameOfRentACar = nameOfRentACar;
		this.address = address;
		this.description = description;
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameOfRentACar() {
		return nameOfRentACar;
	}

	public void setNameOfRentACar(String nameOfRentACar) {
		this.nameOfRentACar = nameOfRentACar;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	

}
