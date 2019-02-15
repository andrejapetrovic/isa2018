package isa.project.reservationRentACar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.project.rentACar.RentACar;

public interface ReservationRentRepo extends JpaRepository<ReservationRentACar, Long> {

	List<ReservationRentACar> findByServis(RentACar rent);
}
