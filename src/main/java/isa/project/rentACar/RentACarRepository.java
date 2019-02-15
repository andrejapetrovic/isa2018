package isa.project.rentACar;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isa.project.branchOffice.BranchOffice;

@Repository
public interface RentACarRepository extends JpaRepository<RentACar, Long> {
	
	RentACar findByNameOfRentACar(String nameOfRentACar);
	
	RentACar findByAddress(String address);
	
	RentACar findByBranches(BranchOffice b);

}
