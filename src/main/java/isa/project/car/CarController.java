package isa.project.car;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.project.branchOffice.BranchOffice;
import isa.project.branchOffice.BranchOfficeRepo;



@RestController
@RequestMapping(value="car")
public class CarController {

	@Autowired
	CarRepository carRepo;
	
	@Autowired
	BranchOfficeRepo branchOfficeRepo;
	
	
	@RequestMapping(
			value = "all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Car>> getAll() {
		List<Car> cars = carRepo.findAll();
		return new ResponseEntity<List<Car>>(cars, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "add",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Car> add(@RequestBody CarDto carDto) throws Exception {
		Car car = new Car();
		car.setModelNumber(carDto.getModelNumber());
		car.setModelName(carDto.getModelName());
		car.setNumberOfSeats(carDto.getNumberOfSeats());
		car.setNumberOfCases(carDto.getNumberOfCases());
		car.setNumberOfDoors(carDto.getNumberOfDoors());
		car.setPricePerDay(carDto.getPricePerDay());
		car.setDescription(carDto.getDescription());
		car.setCarType(CarType.valueOf(carDto.getCarType()));
		
		BranchOffice b = branchOfficeRepo.getOne(carDto.getId()); 
		b.getCars().add(car);
		car.setBranchOffice(b);
		
		
		return new ResponseEntity<>(carRepo.save(car), HttpStatus.CREATED);
		
	}
	
	
	
	
}
