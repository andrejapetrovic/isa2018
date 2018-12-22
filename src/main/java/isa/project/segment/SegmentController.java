package isa.project.segment;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isa.project.airplane.Airplane;
import isa.project.airplane.AirplaneRepository;

@RestController
@RequestMapping(value="segment")
public class SegmentController {

	@Autowired
	SegmentRepository segmentRepo;
	
	@Autowired
	AirplaneRepository airplaneRepo;
	
	@RequestMapping(
			value = "get/{planeId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Segment>> get(@PathVariable("planeId") Long id) {
		List<Segment> segments = segmentRepo.findByAirplaneId(id);
		return new ResponseEntity<List<Segment>>(segments, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "save/{planeId}",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@PathVariable("planeId") Long id, @RequestBody SegmentsDto segDto) {
		
		Airplane a = airplaneRepo.getOne(id);
		if(!segDto.getAdded().isEmpty()) {
			a.getSegments().addAll(segDto.getAdded());
		}		
		
		if(!segDto.getDeleted().isEmpty()) {
			segDto.getDeleted().forEach(seg->{
				Segment aSeg = a.getSegments().stream().filter(aseg -> seg.getSegmentId().equals(aseg.getId())).findAny().orElse(null);
				a.getSegments().remove(aSeg);
			});
		}
		
		if(!segDto.getUpdated().isEmpty()) {
			segDto.getUpdated().forEach(seg -> {
				Segment aSeg = a.getSegments().stream().filter(aseg -> seg.getSegmentId().equals(aseg.getId())).findAny().orElse(null);
				aSeg.setRows(seg.getRows());
				aSeg.setCols(seg.getCols());
			});
		}
	
		
		airplaneRepo.save(a);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	private Logger logger = LoggerFactory.getLogger(this.getClass());
}
