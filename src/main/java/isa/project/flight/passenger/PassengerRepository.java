package isa.project.flight.passenger;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

	List<Passenger> findAll();
	
}
