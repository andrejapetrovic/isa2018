package isa.project.flight;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FlightRepository extends JpaRepository<Flight, Long>, FlightRepositoryCustom {

	List<Flight> findByAirlineId(Long id);
	
}
