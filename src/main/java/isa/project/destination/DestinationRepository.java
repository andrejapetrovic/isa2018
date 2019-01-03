package isa.project.destination;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

	Destination findByAirportCode(String code);
	
	@Query(value = "SELECT * FROM DESTINATION WHERE airport_code IN :airportCodes"
	,nativeQuery = true)
	Set<Destination> findAllByCodes(@Param("airportCodes") List<String> airportCodes);
	
	@Query(value = "SELECT * FROM DESTINATION WHERE dest_id IN" 
	+ "(SELECT dest_id FROM AIRLINE_DESTINATIONS WHERE airline_id = :airlineId)"
	,nativeQuery = true)
	List<Destination> findByAirline(@Param("airlineId") Long airlineId);
	
	@Query(value = "SELECT * FROM DESTINATION WHERE dest_id NOT IN" 
	+ "(SELECT dest_id FROM AIRLINE_DESTINATIONS WHERE airline_id = :airlineId) "
	+ "AND CONCAT(city, country, airport_code, airport) LIKE CONCAT('%', :input, '%')"
	,nativeQuery = true)
	List<Destination> filterNonAddedByAirline(@Param("airlineId") Long airlineId, @Param("input") String input);
	
	@Query(value = "SELECT * FROM DESTINATION "
			+"WHERE CONCAT(city, country, airport_code, airport) LIKE CONCAT('%', :input, '%')"  
	,nativeQuery = true)
	List<Destination> filter(@Param("input") String input);
	
	@Query(value = "SELECT * FROM DESTINATION WHERE dest_id IN" 
	+ "(SELECT dest_id FROM FLIGHT_STOPS WHERE flight_id in :ids)"  
	,nativeQuery = true)
	List<Destination> findStops(@Param("ids") List<Long> flightIds);
}
