package isa.project.segment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="segment")
public class SegmentController {

	@Autowired
	SegmentRepository segmentRepo;
	
	@RequestMapping(
			value = "get/{planeId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Segment>> get(@PathVariable("planeId") Long id) {
		List<Segment> segments = segmentRepo.findByAirplaneId(id);
		return new ResponseEntity<List<Segment>>(segments, HttpStatus.OK);
	}
	
}
