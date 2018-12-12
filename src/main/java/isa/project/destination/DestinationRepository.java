package isa.project.destination;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

	Destination findByAirportCode(String code);
	
	@Query(value = "SELECT * FROM rezervacija.DESTINATION WHERE airport_code IN :airportCodes"
	,nativeQuery = true)
	Set<Destination> findAllByCodes(@Param("airportCodes") List<String> airportCodes);
	
	@Query(value = "SELECT * FROM rezervacija.DESTINATION WHERE dest_id IN" 
	+ "(SELECT dest_id FROM rezervacija.AIRLINE_DESTINATIONS WHERE airline_id = :airlineId)"
	,nativeQuery = true)
	List<Destination> findByAirline(@Param("airlineId") Long airlineId);
	
	@Query(value = "SELECT * FROM rezervacija.DESTINATION WHERE dest_id NOT IN" 
	+ "(SELECT dest_id FROM rezervacija.AIRLINE_DESTINATIONS WHERE airline_id = :airlineId)"
	,nativeQuery = true)
	List<Destination> findNonAddedByAirline(@Param("airlineId") Long airlineId);
}
