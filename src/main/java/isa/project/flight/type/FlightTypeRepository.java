package isa.project.flight.type;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightTypeRepository extends JpaRepository<FlightType, Long> {

	List<FlightType> findAll();
	
}
