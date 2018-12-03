package isa.project.airplane;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

	List<Airplane> findByOwnerId(Long id);
	
	Airplane findByModelNumberAndModelName(int modelNum, String modelName);
}
