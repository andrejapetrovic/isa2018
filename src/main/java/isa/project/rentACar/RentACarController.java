package isa.project.rentACar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value="RentACar")
public class RentACarController {
	
	@Autowired
	RentACarRepository rentACarRepo;
	
	@RequestMapping(
			value = "all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RentACar>> getAll() {
		List<RentACar> service = rentACarRepo.findAll();
		return new ResponseEntity<List<RentACar>>(service, HttpStatus.OK);
	}
	
	
	@RequestMapping(
			value = "add",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RentACar> add(@RequestBody RentACarDto rentACarDto) throws Exception {
		RentACar service = new RentACar();
		service.setName(rentACarDto.getName());
		service.setAddress(rentACarDto.getAddress());
		service.setDescription(rentACarDto.getDescription());
		service.setRating(rentACarDto.getRating());
		
		System.out.println(rentACarDto.getId());
		
		return new ResponseEntity<>(rentACarRepo.save(service), HttpStatus.CREATED);
		
	}
	 
}
