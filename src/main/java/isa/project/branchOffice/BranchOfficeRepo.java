package isa.project.branchOffice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.project.rentACar.RentACar;

public interface BranchOfficeRepo extends JpaRepository<BranchOffice, Long> {
		
	List<BranchOffice> findByservis(RentACar id);
	
	BranchOffice findByAddress(String adr);
	
}
