package isa.project.reservationRentACar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import isa.project.branchOffice.BranchOffice;
import isa.project.branchOffice.BranchOfficeRepo;
import isa.project.car.Car;
import isa.project.car.CarRepository;
import isa.project.rentACar.RentACar;
import isa.project.rentACar.RentACarRepository;
import isa.project.user.User;
import isa.project.user.UserRepository;

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
	
	@Autowired
	UserRepository userRepo;
	
	
	@RequestMapping(
			value = "add",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReservationRentACar> add(@RequestBody ReservationRentACarDto reseRentACarDto) throws Exception {
		User user = userRepo.findById(reseRentACarDto.getUserId());
		//Datume prebacim u odgovarajuci format
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
		   
		Date pickDate = new Date();
		Date dropDate = new Date();
		
		try {
			pickDate = df.parse(reseRentACarDto.getPickUpDate());
			dropDate = df.parse(reseRentACarDto.getDropOfDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ReservationRentACar res = new ReservationRentACar();
		//auto
		Car c = carRepo.findByModelNumber(reseRentACarDto.getModelNumber());
		
		BranchOffice b = branchOfficeRepo.findByAddress(reseRentACarDto.getPickUpLoc());
		//servis
		RentACar r = rentACarRepo.findByBranches(b);
		
		
		
		List<ReservationRentACar> reservations = resRentRepo.findByServis(b.getServis());
		
		if(!reservations.isEmpty()) 
		{
				for (ReservationRentACar item : reservations) 
				{
						if(item.getCar().getId() == c.getId())
						{
							
							System.out.println(item.getPickUpDate());
						    System.out.println(item.getDropOfDate());
						    
						    if(!item.getPickUpDate().after(pickDate) && !item.getDropOfDate().before(pickDate))
						    {
						    	System.out.println("1 ne moze");
						    }
						    else
						    {
							    
						    	if(pickDate.after(item.getDropOfDate()))
							    {
							    	System.out.println("1 moze");
							    	res.setCar(c);
									res.setServis(r);
									res.setCarType(reseRentACarDto.getCarType());
									res.setPassengers(reseRentACarDto.getPassengers());
									res.setPickUpLoc(reseRentACarDto.getPickUpLoc());
									res.setDropOfLoc(reseRentACarDto.getDropOfLoc());
									res.setPickUpDate(pickDate);
									res.setDropOfDate(dropDate);
							    	c.setReserved(true);
							    	carRepo.save(c);
							    	user.getCars().add(res);
							    	userRepo.save(user);
							    }
							    else if(pickDate.before(item.getPickUpDate()))
							    {
							    	if(dropDate.before(item.getPickUpDate()))
							    	{
							    		System.out.println("2 moze");
							    		res.setCar(c);
							    		res.setServis(r);
							    		res.setCarType(reseRentACarDto.getCarType());
							    		res.setPassengers(reseRentACarDto.getPassengers());
							    		res.setPickUpLoc(reseRentACarDto.getPickUpLoc());
							    		res.setDropOfLoc(reseRentACarDto.getDropOfLoc());
							    		res.setPickUpDate(pickDate);
							    		res.setDropOfDate(dropDate);
							    		c.setReserved(true);
								    	carRepo.save(c);
								    	user.getCars().add(res);
								    	userRepo.save(user);
							    	}
							    	else
							    	{
							    		System.out.println("2 ne moze");
							    	}
							    }
						    }	
							
						}
						else
						{
							res.setCar(c);
							res.setServis(r);
							res.setCarType(reseRentACarDto.getCarType());
							res.setPassengers(reseRentACarDto.getPassengers());
							res.setPickUpLoc(reseRentACarDto.getPickUpLoc());
							res.setDropOfLoc(reseRentACarDto.getDropOfLoc());
							res.setPickUpDate(pickDate);
							res.setDropOfDate(dropDate);
							c.setReserved(true);
					    	carRepo.save(c);
					    	user.getCars().add(res);
					    	userRepo.save(user);
						}
				}
		}
		else
		{
			res.setCar(c);
			res.setServis(r);
			res.setCarType(reseRentACarDto.getCarType());
			res.setPassengers(reseRentACarDto.getPassengers());
			res.setPickUpLoc(reseRentACarDto.getPickUpLoc());
			res.setDropOfLoc(reseRentACarDto.getDropOfLoc());
			res.setPickUpDate(pickDate);
			res.setDropOfDate(dropDate);
			c.setReserved(true);
	    	carRepo.save(c);
	    	user.getCars().add(res);
	    	userRepo.save(user);
		}
		
		
		
		
		if(res.getCar() != null)
			return new ResponseEntity<>(resRentRepo.save(res), HttpStatus.CREATED);
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		
	}
	
	
	
	
	@RequestMapping(
			value = "searchCar",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Car>>
			searchCar(@RequestBody SearchCarDto searchCarDto) {
		
		
		//Datume prebacim u odgovarajuci format
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
		   
		Date pickDate = new Date();
		Date dropDate = new Date();
		
		try {
			pickDate = df.parse(searchCarDto.getPickUpDateTime());
			dropDate = df.parse(searchCarDto.getDropOfDateTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		System.out.println("------------------------------------------------------");
		System.out.println(pickDate);
		System.out.println(dropDate);
		System.out.println("------------------------------------------------------");
		
		BranchOffice b = branchOfficeRepo.findByAddress(searchCarDto.getPickUpLocation());
		List<Car> cars = carRepo.findBybranchOffice(b);
		
		List<Car> returnCars = new ArrayList();
		
		List<ReservationRentACar> res = resRentRepo.findByServis(b.getServis());
		
		
		for(Car c :cars)
		{
			System.out.println(c.getModelName());
			//proveriti da li se tip i broj putnika poklapaju sa kolima iz filijale, ako se poklapaju proveriti da li se datumi uklapaju 
			if(c.getCarType() == searchCarDto.getCarType() && c.getNumberOfSeats() == searchCarDto.getPassengers())
			{
				System.out.println("---------------------------------------- USPESNO"+c.getId());
				if(!res.isEmpty()) 
				{
						for (ReservationRentACar item : res) 
						{
							

							if(item.getCar().getId() == c.getId())
							{
								
								System.out.println(item.getPickUpDate());
							    System.out.println(item.getDropOfDate());
							    
							    if(!item.getPickUpDate().after(pickDate) && !item.getDropOfDate().before(pickDate))
							    {
							    	System.out.println("ne moze");
							    }
							    else
							    {
								    
							    	if(pickDate.after(item.getDropOfDate()))
								    {
								    	System.out.println("moze");
								    	returnCars.add(c);
								    }
								    else if(pickDate.before(item.getPickUpDate()))
								    {
								    	if(dropDate.before(item.getPickUpDate()))
								    	{
								    		System.out.println("moze");
								    		returnCars.add(c);
								    	}
								    	else
								    	{
								    		System.out.println("ne moze");
								    	}
								    }
							    }	
								
							}
							else
							{
								returnCars.add(c);
							}
						    
						}
				}
				else
				{
					returnCars.add(c);
				}
				
				
				
				
			}
		}
		
		
		
		
		return new ResponseEntity<List<Car>>(returnCars, HttpStatus.OK);
	
		
	}
	
	
	
	
	
	@RequestMapping(
			value = "search",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RentACar> 
			search(@RequestBody SearchRentDto searchRentDto) {
		
		//Datume prebacim u odgovarajuci format
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		   
		Date pickDate = new Date();
		Date dropDate = new Date();
		
		try {
			pickDate = df.parse(searchRentDto.getPickUpDate());
			dropDate = df.parse(searchRentDto.getDropOfDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(pickDate);
		System.out.println(dropDate);
		
		
		//pretraga po imenu ili adresi
		RentACar rentACar = new RentACar();
		
		if(rentACarRepo.findByNameOfRentACar(searchRentDto.getNameOrLoc()) == null)
		{
			rentACar = rentACarRepo.findByAddress(searchRentDto.getNameOrLoc());
			if(rentACar == null)
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		else 
		{
			rentACar = rentACarRepo.findByNameOfRentACar(searchRentDto.getNameOrLoc());
		}
		
		List<ReservationRentACar> res = resRentRepo.findByServis(rentACar);
		
		//ako rezervacija nije null znaci ima rezervacija u tom servisu, onda gledam da li ima slobodnih vozila u tom servisu
		if(res != null)
		{
			//ako ima idCar koji nije u rezervaciji vrati RentACar,ako ne onda gledaj dal se datum uklapa
			for (ReservationRentACar item : res) {
			    System.out.println(item.getPickUpDate());
			    System.out.println(item.getDropOfDate());
			    
			    for(BranchOffice br:rentACar.getBranches())
			    {
			    	for(Car cr : br.getCars())
			    	{
			    		if(item.getCar().getId() != cr.getId())
			    		{
			    			return new ResponseEntity<RentACar>(rentACar, HttpStatus.OK);
			    		}
			    	}
			    }   
			    
			    if(!item.getPickUpDate().after(pickDate) && !item.getDropOfDate().before(pickDate))
			    {
			    	System.out.println("ne moze");
			    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			    }
			    else
			    {
			    	if(!pickDate.after(item.getDropOfDate()))
				    {
				    	if(pickDate.before(item.getPickUpDate()))
					    {
					    	if(!dropDate.before(item.getPickUpDate()))
					    	{
					    		System.out.println("ne moze");
					    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					    	}
					    }
				    }
			    }	
			    
			}

		}
		else
		{
			//Nema rezervacija u zadataom servisu vrati servis
			return new ResponseEntity<RentACar>(rentACar, HttpStatus.OK);
		}

		return new ResponseEntity<RentACar>(rentACar, HttpStatus.OK);

	}
	
	
}
