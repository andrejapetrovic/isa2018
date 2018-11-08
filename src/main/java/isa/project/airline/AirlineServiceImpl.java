package isa.project.airline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirlineServiceImpl implements AirlineService{

	@Autowired
	AirlineRepository aircomRepo;
	
	@Override
	public Airline save(Airline aircom) {
		return aircomRepo.save(aircom);
	}

}
