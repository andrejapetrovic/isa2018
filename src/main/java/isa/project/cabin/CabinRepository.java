package isa.project.cabin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CabinRepository extends JpaRepository<Cabin, Long> {

	List<Cabin> findAll();
	
}
