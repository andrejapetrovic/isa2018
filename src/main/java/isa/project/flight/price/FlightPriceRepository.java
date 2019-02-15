package isa.project.flight.price;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FlightPriceRepository extends JpaRepository<FlightPrice, Long>{

	@Query(value = "select * from flight_price where flight_class = :fclass and flight_id = :id",
			nativeQuery=true)
	FlightPrice findOneByFlightClassAndFlightId(@Param("fclass") String fClass,@Param("id") Long id);

	@Query(value = "select * from flight_price where flight_class = :fclass and flight_id in :ids order by field(flight_id, :order)",
			nativeQuery=true)
	List<FlightPrice> findByids(@Param("fclass") String fClass,@Param("ids") List<Long> id, @Param("order") String strIds);
	
}
