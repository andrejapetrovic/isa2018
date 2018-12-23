package isa.project.flight;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import isa.project.flight.dto.FlightSearchDto;

public interface FlightRepository extends JpaRepository<Flight, Long> {

	List<Flight> findByAirlineId(Long id);
	
	@Query(value = "select f.*, c.name as class, t.name, fd.airport_code, td.airport_code " + 
			"from flight f " + 
			"natural join flights_classes fc " + 
			"inner join flight_class c on fc.class_id = c.class_id " + 
			"natural join flights_types ft " + 
			"inner join flight_type t on ft.flight_type_id = t.flight_type_id " + 
			"inner join Destination fd on from_id=fd.dest_id " + 
			"inner join Destination td on to_id=td.dest_id " +
			"where fd.airport_code = :#(#flight.fromDestCode) and td.airport_code = :#(#flight.toDestCode) " +
			"and f.depart_date = :#(#flight.departDate) and f.return_date = :#(#flight.returnDate) " +
			"and c.name = :#(#flight.fclass) t.name = :#(#flight.type) and f.remaining_seats >= :#(#flight.numOfPassengers)"
	,nativeQuery = true)
	List<Flight> filterSearch(@Param("flight") FlightSearchDto flight);
	
}
