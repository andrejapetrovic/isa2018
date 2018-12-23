package isa.project.flight.fclass;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightClassRepository extends JpaRepository<FlightClass, Long> {

	List<FlightClass> findAll();
	
}
