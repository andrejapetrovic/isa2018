package isa.project.seat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {

	List<Seat> findByCabinId(Long id);
	
}
