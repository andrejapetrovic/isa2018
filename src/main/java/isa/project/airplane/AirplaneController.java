package isa.project.airplane;

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
@RequestMapping(value="airplane")
public class AirplaneController {

	@Autowired
	AirplaneRepository airplaneRepo;
	
	@RequestMapping(
			value = "add",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airplane> add(@RequestBody Airplane airplane) throws Exception {
		airplaneRepo.save(airplane);
		return new ResponseEntity<Airplane>(airplane, HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value = "delete/{id}",
			method = RequestMethod.DELETE
			)
	public void delete(@PathVariable("id") Long id) throws Exception {
		airplaneRepo.delete(id);
	}
	
	@RequestMapping(
			value = "{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Airplane> get(@PathVariable("id") Long id) {
		Airplane airplane = airplaneRepo.findOne(id);
		if(airplane != null)
			return new ResponseEntity<Airplane>(airplane, HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(
			value = "all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Airplane>> getAll() {
		List<Airplane> airplanes = airplaneRepo.findAll();
		return new ResponseEntity<List<Airplane>>(airplanes, HttpStatus.OK);
	}
	
}
