package isa.project.branchOffice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.project.aircraft.Aircraft;
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
			value = "{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BranchOffice>> get(@PathVariable("id") Long id) {
		
		RentACar r = rentACarRepo.findOne(id);
		List<BranchOffice> branchOffice = branchOfficeRepo.findByservis(r);
		return new ResponseEntity<List<BranchOffice>>(branchOffice, HttpStatus.OK);
	} 
	
	@RequestMapping(
			value = "getOne/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BranchOffice> getOneBranchOffice(@PathVariable("id") Long id) {
		
		
		BranchOffice branchOffice = branchOfficeRepo.getOne(id);
		System.out.println(branchOffice);
		if(branchOffice == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<BranchOffice>(branchOffice, HttpStatus.OK);
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
	
	@RequestMapping(
			value = "update",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody BranchOfficeDto branchOfficeDto) throws Exception {
		BranchOffice branchOffice = branchOfficeRepo.getOne(branchOfficeDto.getId());
		
		branchOffice.setCity(branchOfficeDto.getCity());
		branchOffice.setAddress(branchOfficeDto.getAddress());
		
		return new ResponseEntity<>(branchOfficeRepo.save(branchOffice), HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "delete/{branchOfficeId}/{serviceId}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<BranchOffice> delete(@PathVariable("branchOfficeId") Long branchOfficeId,
			@PathVariable("serviceId") Long serviceId) throws Exception {
		
		RentACar r = rentACarRepo.findOne(serviceId);
		BranchOffice branchOffice = branchOfficeRepo.findOne(branchOfficeId);
		r.getBranches().remove(branchOffice);
		rentACarRepo.save(r);
		branchOfficeRepo.delete(branchOffice.getId());
		return new ResponseEntity<BranchOffice>(branchOffice, HttpStatus.OK);
	}

}
