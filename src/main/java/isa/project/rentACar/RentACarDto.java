package isa.project.rentACar;

import isa.project.branchOffice.BranchOfficeDto;

public class RentACarDto {
	
	private Long id;
	
	private String name;
	
	private String address;
	
	private String description;
	
	private int rating;
	
	public RentACarDto() {
		
	}

	public RentACarDto(Long id, String name, String address, String description, int rating) {
		super();
		this.id = id;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
