package isa.project.friendrelation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRelationRepository extends JpaRepository<FriendRelation, Long> {

	List<FriendRelation> findByUser1IdOrUser2Id(Long id, Long id2);
	
}
