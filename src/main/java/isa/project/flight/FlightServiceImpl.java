package isa.project.flight;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements FlightService{

	@Autowired
	FlightRepository flightRepo;
	
	@Override
	public Flight save(Flight flight) {
		return flightRepo.save(flight);
	}

	@Override
	public void remove(Long id) {
		flightRepo.delete(id);
	}

	@Override
	public Flight findById(Long id) {
		return flightRepo.findOne(id);
	}

	@Override
	public List<Flight> filter() {
		return null;
	}

}
