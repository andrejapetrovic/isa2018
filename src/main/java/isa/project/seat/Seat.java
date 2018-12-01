package isa.project.seat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import isa.project.flight.Cabin;

@Entity
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seat_id")
	private Long id;

	private String code;
	
	private boolean reserved;
	
	private Cabin cabin;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public Cabin getCabin() {
		return cabin;
	}

	public void setCabin(Cabin cabin) {
		this.cabin = cabin;
	}
	
	
}
