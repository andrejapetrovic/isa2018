package isa.project.flight.pricelist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.project.airline.Airline;
import isa.project.airline.AirlineRepository;
import isa.project.flight.Flight;

@RestController
@RequestMapping(value="prices")
public class PriceListController {

	@Autowired
	AirlineRepository airlineRepo;
	
	@Autowired
	PriceListRepository priceListRepo;
	
	@PreAuthorize("hasRole('ROLE_AirlineAdmin')")
	@RequestMapping(
			value = "add",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PriceList> add(@RequestBody PricesDto prices) {
		Airline airline = airlineRepo.getOne(prices.getAirlineId());
		PriceList priceList = new PriceList();
		priceList.setCarryOnBagFee(prices.getCarryOnBagFee());
		priceList.setAdditionalBagFee(prices.getAdditionalBagFee());
		priceList.setFirstCheckedBagFee(prices.getFirstCheckedBagFee());
		priceList.setSecondCheckedBagFee(prices.getSecondCheckedBagFee());
		priceListRepo.save(priceList);
		airline.setPricelist(priceList);
		airlineRepo.save(airline);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
