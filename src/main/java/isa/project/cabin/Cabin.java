package isa.project.cabin;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import isa.project.aircraft.Aircraft;
import isa.project.seat.Seat;

@Entity
public class Cabin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cabin_id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private FlightClass flight_class;

	@OneToMany(mappedBy="cabin", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Seat> seats; 
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "aircraft_id")
	private Aircraft aircraft;
	
	private boolean configured;
	
	//broj redova kolona,razmak izmedju sedista po x i y, i d = duzina stranice kvadrata koji predstavlja sediste
	private int row, col, dx, dy, d;
	
	//string koji definise raspored sedista, u formatu n1-n2-n3-n... sum(n)<col
	private String separations;
	
	//velicina razmaka izmedju svake odvojene grupe sedista u kabini
	private int separationSize;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FlightClass getFlight_class() {
		return flight_class;
	}

	public void setFlight_class(FlightClass flight_class) {
		this.flight_class = flight_class;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	public boolean isConfigured() {
		return configured;
	}

	public void setConfigured(boolean configured) {
		this.configured = configured;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public String getSeparations() {
		return separations;
	}

	public void setSeparations(String separations) {
		this.separations = separations;
	}

	public int getSeparationSize() {
		return separationSize;
	}

	public void setSeparationSize(int separationSize) {
		this.separationSize = separationSize;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	
}
