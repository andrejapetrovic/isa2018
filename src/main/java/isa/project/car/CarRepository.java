package isa.project.car;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.project.branchOffice.BranchOffice;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
	
	List<Car> findBybranchOffice(BranchOffice id);
	Car findByModelNumber(int i);

}
