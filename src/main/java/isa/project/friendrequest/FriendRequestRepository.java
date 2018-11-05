package isa.project.friendrequest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRequestRepository  extends JpaRepository<FriendRequest, Long> {
	
	List<FriendRequest> findByReqStatusAndReciverId(FriendRequestStatus reqStatus, Long reciverId);
}
