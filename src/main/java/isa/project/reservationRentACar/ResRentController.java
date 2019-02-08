package isa.project.reservationRentACar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.project.branchOffice.BranchOfficeRepo;
import isa.project.car.CarRepository;
import isa.project.rentACar.RentACar;
import isa.project.rentACar.RentACarRepository;

@RestController
@RequestMapping(value="reservationRent")
public class ResRentController {

	@Autowired
	ReservationRentRepo resRentRepo;
	
	@Autowired
	RentACarRepository rentACarRepo;
	
	@Autowired
	BranchOfficeRepo branchOfficeRepo;
	
	@Autowired
	CarRepository carRepo;
	
	
	@RequestMapping(
			value = "search",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RentACar> 
			search(@RequestBody SearchRentDto searchRentDto) {
		
		
		
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		   
		Date pickDate = new Date();
		Date dropDate = new Date();
		
		try {
			pickDate = sm.parse(searchRentDto.getPickUpDate());
			dropDate = sm.parse(searchRentDto.getDropOfDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(pickDate);
		System.out.println(dropDate);
		
		
		RentACar rentACar = new RentACar();
		
		List<ReservationRentACar> resRent = resRentRepo.findAll();
		for (ReservationRentACar item : resRent) {
		    System.out.println(item.getPickUpDate());
		    System.out.println(item.getDropOfDate());
		    
		    if(!item.getPickUpDate().after(pickDate) && !item.getDropOfDate().before(pickDate))
		    {
		    	System.out.println("ne moze");
		    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		    else
		    {
			    
		    	if(pickDate.after(item.getDropOfDate()))
			    {
			    	System.out.println("moze");
			    }
			    else if(pickDate.before(item.getPickUpDate()))
			    {
			    	if(dropDate.before(item.getPickUpDate()))
			    	{
			    		System.out.println("moze");
			    	}
			    	else
			    	{
			    		System.out.println("ne moze");
			    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			    	}
			    }
		    }	
		}
		
		
		
		if(rentACarRepo.findByNameOfRentACar(searchRentDto.getNameOrLoc()) == null)
		{
			rentACar = rentACarRepo.findByAddress(searchRentDto.getNameOrLoc());
			if(rentACar == null)
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			
			return new ResponseEntity<RentACar>(rentACar, HttpStatus.OK);
			
		}
		
		rentACar = rentACarRepo.findByNameOfRentACar(searchRentDto.getNameOrLoc());
		
		return new ResponseEntity<RentACar>(rentACar, HttpStatus.OK);
		
	}
}
