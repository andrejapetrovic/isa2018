package isa.project.branchOffice;

import isa.project.car.CarDto;
import isa.project.rentACar.RentACarDto;

public class BranchOfficeDto {
	
	private Long id;
	
	private String city;
	
	private String address;
	
	public BranchOfficeDto() {
		
	}

	public BranchOfficeDto(Long id, String city, String address) {
		super();
		this.id = id;
		this.city = city;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
}
