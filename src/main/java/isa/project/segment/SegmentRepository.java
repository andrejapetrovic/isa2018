package isa.project.segment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SegmentRepository extends JpaRepository<Segment, Long> {

	@Query(value = "SELECT * FROM rezervacija.SEGMENT s WHERE s.segment_id IN"
			+ "(SELECT a_seg.segment_id FROM rezervacija.AIRPLANE_SEGMENTS a_seg WHERE a_seg.airplane_id = :id)"
			,nativeQuery = true)
	List<Segment> findByAirplaneId(@Param("id") Long id);
	
}
