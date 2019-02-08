package isa.project.flight.seat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import isa.project.cabin.FlightClass;

public interface FlightSeatRepository extends JpaRepository<FlightSeat, Long> {

	List<FlightSeat> findByFlightId(Long id);

	List<FlightSeat> findByFastReservationAndReserved(boolean fastRes, boolean res);
}
