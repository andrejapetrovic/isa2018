package isa.project.segment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SegmentRepository extends JpaRepository<Segment, Long> {

	@Query(value = "SELECT * FROM SEGMENT WHERE segment_id IN"
			+ "(SELECT segment_id FROM AIRPLANE_SEGMENTS WHERE airplane_id = :id)"
			,nativeQuery = true)
	List<Segment> findByAirplaneId(@Param("id") Long id);
	
}
