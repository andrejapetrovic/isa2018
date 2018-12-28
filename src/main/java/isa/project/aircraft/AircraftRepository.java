package isa.project.aircraft;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

	List<Aircraft> findByOwnerId(Long id);
	
	Aircraft findByModelNumberAndModelName(int modelNum, String modelName);
}
