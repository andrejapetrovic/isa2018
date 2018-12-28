package isa.project.flight;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.project.flight.dto.FlightFilterDto;
import isa.project.flight.dto.FlightSearchDto;
import isa.project.flight.dto.FlightType;
import isa.project.flight.dto.Sort;

@Transactional
@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepo;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public List<List<Flight>> searchAndFilter(FlightSearchDto searchDto, FlightFilterDto filterDto) {
		List<List<Flight>> ret = new ArrayList<>();
		if (filterDto.getFtype() == FlightType.One_way) {
			List<Flight> flights = searchOneWays(searchDto, filterDto);
			if( flights == null) {
				return null;
			}
			ret.add(flights);
		}
		else if (filterDto.getFtype() == FlightType.Round_trip) {
			ret = searchRoundTrips(searchDto, filterDto);
		}

		return ret;
	}

	@Override
	public List<Flight> sort(Sort sort, List<BigInteger> ids, Date takeoffTime, Date landingTime, FlightType type) {
		if(ids.isEmpty())
			return null;
		if (sort != null ) {
			if (sort == Sort.cheapest && type == FlightType.One_way) {
				return flightRepo.findByCheapestOneWay(ids);
			} else if (sort == Sort.highest_price && type == FlightType.One_way) {
				return flightRepo.findByHighestPriceOneWay(ids);
			}else if (sort == Sort.cheapest && type == FlightType.Round_trip) {
				return flightRepo.findByCheapestReturn(ids);
			} else if (sort == Sort.highest_price && type == FlightType.Round_trip) {
				return flightRepo.findByHighestPriceReturn(ids);
			} else if (sort == Sort.earliest_takeoff1 || sort == Sort.earliest_takeoff2) {
				return flightRepo.findEarliestTakeoff(ids);
			} else if (sort == Sort.latest_takeoff1 || sort == Sort.latest_takeoff2) {
				return flightRepo.findByLatestTakeoff(ids);
			} else if (sort == Sort.quickest) {
				return flightRepo.findByQuickest(ids, takeoffTime, landingTime);
			} else if (sort.equals(Sort.slowest)) {
				return flightRepo.findBySlowest(ids, takeoffTime, landingTime);
			}
		}
		return flightRepo.findByIds(ids);
	}

	@Override
	public List<BigInteger> filter(List<BigInteger> ids, FlightFilterDto filterDto) {
		if	(!ids.isEmpty()) {
			ids = flightRepo.findByClassAndRemainingSeats(ids, filterDto.getFclass(),
					filterDto.getAdults() + filterDto.getChildren());
		}
		if (filterDto.getStops() != -1 && !ids.isEmpty()) {
			ids = flightRepo.findByStopNum(ids, filterDto.getStops());
		}
		if (filterDto.getAirlines() != null && !ids.isEmpty()) {
			List<Long> airlineIds = new ArrayList<>(Arrays.asList(filterDto.getAirlines().split("-"))).stream()
					.map(Long::parseLong)
					.collect(Collectors.toList());
			ids = flightRepo.findByAirlines(ids, airlineIds);
		}
		if (filterDto.getStopDests() != null && !ids.isEmpty()) {
			List<String> codes = new ArrayList<String>(Arrays.asList(filterDto.getStopDests().split("-")));
			ids = flightRepo.findByStops(ids, codes);
		}
		if (filterDto.getPriceRange() != null && !ids.isEmpty()) {
			String prices[] = filterDto.getPriceRange().split("-");
			if (filterDto.getFtype() == FlightType.Round_trip) {
				ids = flightRepo.findByReturnPriceRange(ids, prices[0], prices[1]);
			}
			else if (filterDto.getFtype() == FlightType.One_way) {
				ids = flightRepo.findByOneWayPriceRange(ids, prices[0], prices[1]);
			}
		}
		return ids;
	}

	@Override
	public List<List<Flight>> searchRoundTrips(FlightSearchDto searchDto, FlightFilterDto filterDto) {
		List<Flight> flightsFrom = searchOneWays(searchDto, filterDto);
		
		List<BigInteger> ids = flightRepo.findIds(
				searchDto.getToDest(), searchDto.getFromDest(), searchDto.getDepartDate());
		ids = filter(ids, filterDto);
		List<Flight> flightsTo = sort(filterDto.getSort(), ids, filterDto.getTakeoffTime1(), filterDto.getLandingTime1(), filterDto.getFtype());
		
		if(flightsFrom == null || flightsTo == null) {
			return null;
		}
		
		List<List<Flight>> ret = new ArrayList<>();
		ret.add(flightsFrom);
		ret.add(flightsTo);
		return ret;
	}

	@Override
	public List<Flight> searchOneWays(FlightSearchDto searchDto, FlightFilterDto filterDto) {
		List<BigInteger> ids = flightRepo.findIds(
				searchDto.getFromDest(), searchDto.getToDest(), searchDto.getDepartDate());
		logger.info("lista " + ids.size());
		List<Flight> flights = null;
		ids = filter(ids, filterDto);
		flights = sort(
				filterDto.getSort(), ids, filterDto.getTakeoffTime1(), filterDto.getLandingTime1(), filterDto.getFtype());
			
		return flights;
	}


}
