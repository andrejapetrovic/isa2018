package isa.project.rentACar;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import isa.project.branchOffice.BranchOffice;



@Entity
public class RentACar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String nameOfRentACar;
	
	@Column(unique = true, nullable = false)
	private String address;
	
	private String description;
	
	private int rating;

	@OneToMany(mappedBy="servis",fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonIgnore
	private List<BranchOffice> branches;
	
	public RentACar() {
		
	}

	public RentACar(Long id, String nameOfRentACar, String address, String description, int rating,
			List<BranchOffice> branches) {
		super();
		this.id = id;
		this.nameOfRentACar = nameOfRentACar;
		this.address = address;
		this.description = description;
		this.rating = rating;
		this.branches = branches;
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

	public List<BranchOffice> getBranches() {
		return branches;
	}

	public void setBranches(List<BranchOffice> branches) {
		this.branches = branches;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	

}
