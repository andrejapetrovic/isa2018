package isa.project.flight.reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import isa.project.flight.seat.FlightSeat;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query(value="select * from reservation where email = :email", nativeQuery=true)
	List<Reservation> findByEmail(@Param("email") String email);
	
}
