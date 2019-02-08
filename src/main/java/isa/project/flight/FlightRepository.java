package isa.project.flight;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import isa.project.cabin.FlightClass;

public interface FlightRepository extends JpaRepository<Flight, Long> {

	List<Flight> findByAirlineId(Long id);
	
	@Query(value = "SELECT * FROM flight WHERE flight_id IN :ids"   
			,nativeQuery = true)
	List<Flight> findByIds(
			@Param("ids") List<BigInteger> ids);
	
	@Query(value = "SELECT flight_id FROM flight "
					+"INNER JOIN destination df on from_id = df.dest_id " 
					+"INNER JOIN destination dt on to_id = dt.dest_id "
					+"WHERE df.airport_code = :fromCode and dt.airport_code = :toCode and DATE(departure_date) = :departure"
			,nativeQuery = true)
	List<BigInteger> findIds(
			@Param("fromCode") String fromCode, 
			@Param("toCode") String toCode,
			@Param("departure") Date departure
			);
	
	@Query(value = "SELECT * FROM flight WHERE flight_id IN :ids order by one_way_price asc"   
			,nativeQuery = true)
	List<Flight> findByCheapestOneWay(
			@Param("ids") List<BigInteger> ids);
	
	@Query(value = "SELECT * FROM flight WHERE flight_id IN :ids order by one_way_price desc"   
			,nativeQuery = true)
	List<Flight> findByHighestPriceOneWay(
			@Param("ids") List<BigInteger> ids);

	@Query(value = "SELECT * FROM flight WHERE flight_id IN :ids order by return_price asc"   
			,nativeQuery = true)
	List<Flight> findByCheapestReturn(
			@Param("ids") List<BigInteger> ids);
	
	@Query(value = "SELECT * FROM flight WHERE flight_id IN :ids order by return_price desc"   
			,nativeQuery = true)
	List<Flight> findByHighestPriceReturn(
			@Param("ids") List<BigInteger> ids);
	
	@Query(value = "SELECT * FROM flight WHERE flight_id IN :ids order by TIME(departure_date) asc"   
			,nativeQuery = true)
	List<Flight> findEarliestTakeoff(
			@Param("ids") List<BigInteger> ids);
	
	@Query(value = "SELECT * FROM flight WHERE flight_id IN :ids order by TIME(departure_date) desc"   
			,nativeQuery = true)
	List<Flight> findByLatestTakeoff(
			@Param("ids") List<BigInteger> ids);
	
	@Query(value = "SELECT * FROM flight WHERE flight_id IN :ids order by TIME(landing_date) asc"   
			,nativeQuery = true)
	List<Flight> findByEarliestLanding(
			@Param("ids") List<BigInteger> ids);
	
	@Query(value = "SELECT * FROM flight WHERE flight_id IN :ids order by TIME(landing_date) desc"   
			,nativeQuery = true)
	List<Flight> findByLatestLanding(
			@Param("ids") List<BigInteger> ids);
	
	@Query(value = "SELECT * FROM flight WHERE flight_id IN :ids order by (landing_date - departure_date) asc"   
			,nativeQuery = true)
	List<Flight> findByQuickest(
			@Param("ids") List<BigInteger> ids
			);
	@Query(value = "SELECT * FROM flight WHERE flight_id IN :ids order by (landing_date - departure_date) desc"   
			,nativeQuery = true)
	List<Flight> findBySlowest(
			@Param("ids") List<BigInteger> ids
			);
	
	@Query(value = "SELECT flight_id FROM flight "
			+"NATURAL JOIN airline " 
			+"WHERE flight_id in :flightIds and airline.airline_id in :airlineIds"
	,nativeQuery = true)
	List<BigInteger> findByAirlines(
			@Param("flightIds") List<BigInteger> flightIds,
			@Param("airlineIds") List<Long> airlineIds);
	
	@Query(value = "SELECT flight_id FROM flight WHERE flight_id in :ids and stop_count = :stops"
	,nativeQuery = true)
	List<BigInteger> findByStopNum(
			@Param("ids") List<BigInteger> ids,
			@Param("stops") int stops);
	
	@Query(value = "SELECT flight_id FROM flight WHERE flight_id in :ids and stop_count >= 2"
	,nativeQuery = true)
	List<BigInteger> findByStopsGreaterThan1(
			@Param("ids") List<BigInteger> ids
			);
	
	@Query(value = "SELECT flight_id FROM flight "
			+"NATURAL JOIN flight_stops " 
			+"INNER JOIN destination dest on flight_stops.dest_id = dest.dest_id " 
			+"WHERE flight_id in :ids and dest.airport_code in :codes"
	,nativeQuery = true)
	List<BigInteger> findByStops(
			@Param("ids") List<BigInteger> ids, 
			@Param("codes") List<String> codes);
	
	@Query(value = "select flight_id from flight NATURAL JOIN flight_seat " + 
			"WHERE flight_id in :ids and flight_class = :flightClass and reserved = false " + 
			"group by flight_id having count(reserved) >= :passNum"
	,nativeQuery = true)
	List<BigInteger> findByClassAndRemainingSeats(
			@Param("ids") List<BigInteger> ids, 
			@Param("flightClass") String className, 
			@Param("passNum") int passNum);
	
	@Query(value = "SELECT flight_id FROM flight WHERE flight_id IN :ids and one_way_price BETWEEN :lowest AND :highest"   
			,nativeQuery = true)
	List<BigInteger> findByOneWayPriceRange(
			@Param("ids") List<BigInteger> ids,
			@Param("lowest") String lowest,
			@Param("highest") String highest
			);
	
	@Query(value = "SELECT f1.flight_id from flight f1 cross join flight f2 where f1.flight_id IN :ids and f1.from_id=f2.to_id and f2.from_id=f1.to_id and f1.airline_id = f2.airline_id and (f1.one_way_price + f2.return_price) BETWEEN :lowest AND :highest"   
			,nativeQuery = true)
	List<BigInteger> findByRoundTripPriceFlight(
			@Param("ids") List<BigInteger> ids,
			@Param("lowest") String lowest,
			@Param("highest") String highest
			);
	@Query(value = "SELECT f1.flight_id from flight f1 cross join flight f2 where f1.flight_id IN :ids and f1.from_id=f2.to_id and f2.from_id=f1.to_id and f1.airline_id = f2.airline_id and (f1.return_price + f2.one_way_price) BETWEEN :lowest AND :highest"   
			,nativeQuery = true)
	List<BigInteger> findByRoundTripPriceReturnFlight(
			@Param("ids") List<BigInteger> ids,
			@Param("lowest") String lowest,
			@Param("highest") String highest
			);
	
	@Query(value = "SELECT flight_id FROM flight WHERE flight_id IN :ids and HOUR(TIMEDIFF(landing_date, departure_date)) >= :lowest "
			+ "and HOUR(TIMEDIFF(landing_date, departure_date)) <= :highest"   
			,nativeQuery = true)
	List<BigInteger> findByDuration(
			@Param("ids") List<BigInteger> ids,
			@Param("lowest") String lowest,
			@Param("highest") String highest
			);
	
	List<Flight> findByAirplaneId(Long id);
}
