package isa.project.airplane;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import isa.project.segment.Segment;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

}
