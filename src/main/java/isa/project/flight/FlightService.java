package isa.project.flight;

import java.util.List;

public interface FlightService {

	Flight save(Flight flight);
	
	void remove(Long id);
	
	Flight findById(Long id);
	
	List<Flight> filter();
	
}
