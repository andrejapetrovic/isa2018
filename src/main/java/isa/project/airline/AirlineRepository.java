package isa.project.airline;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, Long>{

	Airline findByNameContainingIgnoreCase(String name);

}
