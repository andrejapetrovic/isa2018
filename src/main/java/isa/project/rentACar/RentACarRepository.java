package isa.project.rentACar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentACarRepository extends JpaRepository<RentACar, Long> {

}
