package isa.project.branchOffice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.project.rentACar.RentACar;
import isa.project.rentACar.RentACarRepository;


@RestController
@RequestMapping(value="branchOffice")
public class BranchOfficeController {
	
	@Autowired
	BranchOfficeRepo branchOfficeRepo;
	
	@Autowired
	RentACarRepository rentACarRepo;
	
	@RequestMapping(
			value = "all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BranchOffice>> getAll() {
		List<BranchOffice> branchOffice = branchOfficeRepo.findAll();
		return new ResponseEntity<List<BranchOffice>>(branchOffice, HttpStatus.OK);
	} 
	
	@RequestMapping(
			value = "add",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BranchOffice> add(@RequestBody BranchOfficeDto branchOfficeDto) throws Exception {
		BranchOffice branchOffice = new BranchOffice();
		branchOffice.setCity(branchOfficeDto.getCity());
		branchOffice.setAddress(branchOfficeDto.getAddress());
		

		System.out.println(branchOfficeDto.getId());
		RentACar r = rentACarRepo.findOne(branchOfficeDto.getId());
		r.getBranches().add(branchOffice);
		branchOffice.setServis(r);
		
		
		return new ResponseEntity<>(branchOfficeRepo.save(branchOffice), HttpStatus.CREATED);
		
	}

}
