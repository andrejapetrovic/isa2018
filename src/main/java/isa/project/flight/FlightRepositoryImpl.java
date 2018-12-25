package isa.project.flight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import isa.project.flight.dto.FlightFilterDto;
import isa.project.flight.dto.Sort;

@Repository
@Transactional(readOnly = true)
public class FlightRepositoryImpl implements FlightRepositoryCustom {
	
    @PersistenceContext
    private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Flight> filterQuery(FlightFilterDto flightDto) {
		StringBuilder queryString = new StringBuilder(
				"select f.*, fclass.name, ftype.name, from_dest.airport_code, to_dest.airport_code, stop_dest.airport_code ")
		.append("from flight f ")
		.append("natural join flight_classes fc ")
		.append("inner join flight_class fclass on fc.class_id = fclass.class_id ")
		.append("natural join flight_types ft ")
		.append("inner join flight_type ftype on ft.flight_type_id = ftype.flight_type_id ")
		.append("inner join Destination from_dest on from_id=from_dest.dest_id ")
		.append("inner join Destination to_dest on to_id=to_dest.dest_id ")
		.append("inner join flight_stops f_stops on f_stops.flight_id = f.flight_id ")
		.append("inner join destination stop_dest on stop_dest.dest_id = f_stops.dest_id");

		ArrayList<Object> values = new ArrayList<>();
		queryString.append(" where from_dest.airport_code = ?");
		values.add(flightDto.getFromDest());
		queryString.append(" and to_dest.airport_code = ?");
		values.add(flightDto.getToDest());
		queryString.append(" and depart_date = ?");
		values.add(flightDto.getDepartDate());
		queryString.append(" and return_date = ?");
		values.add(flightDto.getReturnDate());
		queryString.append(" and ftype.name = ?");
		values.add(flightDto.getFtype());
		queryString.append(" and fclass.name = ?");
		values.add(flightDto.getFclass());
		queryString.append(" and remaining_seats >= ?");
		values.add(flightDto.getPassNum());
		if(flightDto.getPriceRange() != null) {
			String[] price = flightDto.getPriceRange().split("-");
			queryString.append(" and price between  ? and ?");
			values.add(price[0]);
			values.add(price[1]);
		}
		if(flightDto.getStops() != -1) {
			queryString.append(" and stop_count = ?");
			values.add(flightDto.getStops());
		}
		if(flightDto.getAirlines() != null) {
			queryString.append(" and");
			List<String> airlines = Arrays.asList(flightDto.getAirlines().split("-"));
			for (String airline : airlines) {
				queryString.append(" airline_id = ? or");
				values.add(airline);
			}
			queryString.replace(queryString.length()-2, queryString.length(), "");
		}
		if(flightDto.getStopDests() != null) {
			queryString.append(" and");
			List<String> stops = Arrays.asList(flightDto.getStopDests().split("-"));
			for (String stop : stops) {
				queryString.append(" stop_dest.airport_code = ? or");
				values.add(stop);
			}
			queryString.replace(queryString.length()-2, queryString.length(), "");
		}		
		if(flightDto.getSort() != null) {
			queryString.append(" order by ");
			if(flightDto.getSort().equals(Sort.cheapest)) 
				queryString.append("price asc");
			else if(flightDto.getSort().equals(Sort.highest_price)) 
				queryString.append("price desc");
			else if(flightDto.getSort().equals(Sort.earliest_takeoff1)) 
				queryString.append("takeoff_time1 asc");
			else if(flightDto.getSort().equals(Sort.latest_takeoff1)) 
				queryString.append("takeoff_time1 desc");
			else if(flightDto.getSort().equals(Sort.earliest_takeoff2)) 
				queryString.append("takeoff_time2 asc");
			else if(flightDto.getSort().equals(Sort.latest_takeoff2)) 
				queryString.append("takeoff_time2 desc");
			else if(flightDto.getSort().equals(Sort.quickest)) 
				queryString.append("abs(takeoff_time1-landing_time1) asc");
			else if(flightDto.getSort().equals(Sort.slowest)) 
				queryString.append("abs(takeoff_time1-landing_time1) desc");
		}
		queryString.append(" group by flight_id");
		Query query = entityManager.createNativeQuery(queryString.toString(), Flight.class);
		for (int i = 0; i < values.size(); i++) {
				logger.info(values.get(i)+"");
				
			query.setParameter(i+1, values.get(i));
		}
		
		return query.getResultList();
	}
	private Logger logger = LoggerFactory.getLogger(this.getClass());
}
