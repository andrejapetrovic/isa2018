package isa.project.destination;

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

@RestController
@RequestMapping(value="dest")
public class DestinationController {

	@Autowired
	DestinationRepository destRepo;
	
	@RequestMapping(
			value = "add",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Destination> add(@RequestBody Destination dest) throws Exception {
		destRepo.save(dest);
		return new ResponseEntity<Destination>(dest, HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value = "delete/{id}",
			method = RequestMethod.DELETE
			)
	public void delete(@PathVariable("id") Long id) throws Exception {
		destRepo.delete(id);
	}
	
	@RequestMapping(
			value = "{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Destination> get(@PathVariable("id") Long id) {
		Destination dest = destRepo.findOne(id);
		if(dest != null)
			return new ResponseEntity<Destination>(dest, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(
			value = "all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Destination>> getAll() {
		List<Destination> dests = destRepo.findAll();
		return new ResponseEntity<List<Destination>>(dests, HttpStatus.OK);
	}
	
}
