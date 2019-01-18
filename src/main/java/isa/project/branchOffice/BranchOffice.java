package isa.project.branchOffice;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


import isa.project.car.Car;
import isa.project.rentACar.RentACar;


@Entity
public class BranchOffice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="city", nullable = false)
	private String city;

	@Column(name="address", nullable = false)
	private String address;
	
	@ManyToOne
    @JoinColumn(name = "servis_id")
	private RentACar servis;
	
	@OneToMany(mappedBy = "branchOffice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
	private List<Car> cars;

	public BranchOffice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BranchOffice(Long id, String city, String address, RentACar servis, List<Car> cars) {
		super();
		this.id = id;
		this.city = city;
		this.address = address;
		this.servis = servis;
		this.cars = cars;
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

	public RentACar getServis() {
		return servis;
	}

	public void setServis(RentACar servis) {
		this.servis = servis;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
